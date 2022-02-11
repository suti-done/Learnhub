package com.Learnhub.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.Learnhub.entity.tasks;

public interface StorageService {
	void init();

;

	void deleteAll();

	Stream<Path> loadAll(String location,tasks task);

	

	void matupload(MultipartFile file, tasks task);


	void subupload(MultipartFile file, tasks task);


	Path load(String filename, String file);


	Resource loadAsResource(String filename, String file);

}
