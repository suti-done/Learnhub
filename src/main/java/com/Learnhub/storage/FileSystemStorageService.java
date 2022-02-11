package com.Learnhub.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Learnhub.dao.tutorDao;
import com.Learnhub.dao.tutorDaoImpl;
import com.Learnhub.entity.Materials;
import com.Learnhub.entity.Submission;
import com.Learnhub.entity.tasks;

@Service
public class FileSystemStorageService implements StorageService {
	
	
	private final Path matLocation;
	private final Path subLocation;
	
	@Autowired
	tutorDao tutorDao;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.matLocation = Paths.get(properties.getmatLocation());
		this.subLocation = Paths.get(properties.getSublocation());
	} 

	@Override
	public void init() {
		try {
			Files.createDirectory(matLocation);
			Files.createDirectory(subLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}

	}

	@Override
	public void matupload(MultipartFile file, tasks task) {
	
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path destinationFile = this.matLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			
			if (!destinationFile.getParent().equals(this.matLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
			Materials materials= new Materials();
			materials.setType(file.getContentType().split("/")[1]);
			materials.setName(file.getOriginalFilename().trim());
			System.out.println(task.getId());
			materials.setTasks(task);
			
			tutorDao.saveMaterials(materials);
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
		

	}
	
	@Override
	public void subupload(MultipartFile file, tasks task) {
	
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			Path destinationFile = this.subLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			
			if (!destinationFile.getParent().equals(this.subLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
			Submission submission= new Submission();
			submission.setType(file.getContentType().split("/")[1]);
			submission.setName(file.getOriginalFilename().trim());
			System.out.println(task.getId());
			submission.setTask(task);
			
			tutorDao.saveSubmission(submission);
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
		
		

	}

	@Override
	public Stream<Path> loadAll(String file,tasks task) {
		Path location;
		//List<String> list= Arrays.asList("Sutirtha Brahmachari report  (5).pdf","Kaagaz_20211231_212114461647 (1) (1).pdf","Overall_Report_1957540_en_US");
		final List<String> list;
		
		System.out.println(file+" load all");
		if(file=="mat")
		{
			location=matLocation;
			list=tutorDao.getMaterials(task);
		}
		else{
			location=subLocation;
			list=tutorDao.getSubmissions(task);
		}
		try {
			return Files.walk(location, 1)
				.filter(path -> !path.equals(location))
				.filter(p -> list.contains(p.getFileName().toString()))
				.map(location::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
		
		//return null;
	}

	@Override
	public Path load(String filename, String file) {
		Path location;
		System.out.println(file);
		if(file=="mat")
		{
			location=matLocation;
		}
		else{
			location=subLocation;
		}
		return location.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename,String file_p) {
		
		try {
			Path file = load(filename,file_p);
			Resource resource = new UrlResource(file.toUri());
			System.out.println(resource.exists());
			System.out.println(resource.isReadable());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(matLocation.toFile());
		FileSystemUtils.deleteRecursively(subLocation.toFile());
	}

}
