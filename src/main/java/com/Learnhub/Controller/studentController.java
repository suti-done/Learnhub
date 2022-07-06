package com.Learnhub.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Learnhub.dao.studentDao;
import com.Learnhub.entity.Course;
import com.Learnhub.entity.tasks;
import com.Learnhub.storage.StorageFileNotFoundException;
import com.Learnhub.storage.StorageService;

@Controller
@RequestMapping("/student")
@SessionAttributes({"courses","course","task"})

public class studentController {
	
	@Autowired
	private studentDao studentDaoImpl;
	
	
	private final StorageService storageService;
	
	@Autowired
	public studentController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@RequestMapping("/courses")
	public String getCourse(Model theModel)
	{
		
		List<Course> courses=studentDaoImpl.getCourses();
		
		theModel.addAttribute("courses", courses);
		
		theModel.addAttribute("course", new Course());
		
		return "courses.jsp";
	}
	
	
  
	
	@RequestMapping("/taskDetails")
	public String taskDetails(@ModelAttribute("course") Course course, Model model)
	{
	   Course theCourse=studentDaoImpl.getCourse(course);	
	   List<tasks> task= theCourse.getTask();
	   
	   model.addAttribute("tasks",task);
	   
	   
	   model.addAttribute("course", course);
	   
	   model.addAttribute("task",new tasks());
	    
		return "task_details.jsp";
	}
	
	
	
	
	@RequestMapping("/materials")
	public String showMaterials(@ModelAttribute("course") Course course,Model theModel,@ModelAttribute("task") tasks task)
	{
		
		
		   tasks thetask = studentDaoImpl.getTask(task);
		   
		   Course theCourse=studentDaoImpl.getCourse(thetask.getCourse());
		   theModel.addAttribute("course",theCourse);
		   theModel.addAttribute("task", thetask);
		   
			
			theModel.addAttribute("files", storageService.loadAll("mat",task).map(path -> MvcUriComponentsBuilder
																		.fromMethodName(studentController.class,"serveFileMAT", path.getFileName().toString())
																		.build()
																		.toUri()
																		.toString()
																		)
					                                                .collect(Collectors.toList())
					              );
			
		return "materials.jsp";
	}
	
	@GetMapping("/matfiles/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFileMAT(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename,"mat");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"inline; filename=\""
		        + file.getFilename()
		        + "\"")
				.contentType(MediaType.APPLICATION_PDF)
				.body(file);
	}
	@GetMapping("/subfiles/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFileSUB(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename,"sub");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"inline; filename=\""
		        + file.getFilename()
		        + "\"")
				.contentType(MediaType.APPLICATION_PDF)
				.body(file);
	}
	
	@RequestMapping("/submission")
	public String showSubmissions(@ModelAttribute("course") Course course,Model theModel,@ModelAttribute("task") tasks task)
	{
		  
		   tasks thetask = studentDaoImpl.getTask(task);
		   
		   theModel.addAttribute("task", thetask);
		   Course theCourse=studentDaoImpl.getCourse(thetask.getCourse());
		   theModel.addAttribute("course",theCourse);
			
			theModel.addAttribute("files", storageService.loadAll("sub",task).map(path -> MvcUriComponentsBuilder
														.fromMethodName(studentController.class,"serveFileSUB", path.getFileName().toString())
														.build()
														.toUri()
														.toString()
														)
									                .collect(Collectors.toList())
                             );

			
		return "submissions.jsp";
	}
	@PostMapping("/uploadSUB")
	public String handleFileUploadSub(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes,@ModelAttribute("task") tasks task) {

		tasks tasks=studentDaoImpl.getTask(task);
		storageService.subupload(file,tasks);
		
		Course theCourse=studentDaoImpl.getCourse(task.getCourse().getId());
		   
		redirectAttributes.addFlashAttribute("course",theCourse);
		return "redirect:/tutor/taskDetails";
	}
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
}
