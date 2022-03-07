package com.Learnhub.Controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.Learnhub.dao.studentDaoImpl;
import com.Learnhub.dao.tutorDaoImpl;
import com.Learnhub.entity.Course;
import com.Learnhub.entity.Student;
import com.Learnhub.entity.tasks;
import com.Learnhub.storage.StorageFileNotFoundException;
import com.Learnhub.storage.StorageService;

@Controller
@RequestMapping("/tutor")
@SessionAttributes({"courses","course","task"})

public class tutorController {
	
	@Autowired
	private tutorDaoImpl tutorDao;
	
	
	private final StorageService storageService;
	
	@Autowired
	private studentDaoImpl studentDaoImpl;
	
	@Autowired
	public tutorController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@RequestMapping("/courses")
	public String getCourse(Model theModel)
	{
		
		List<Course> courses=tutorDao.getCourses();
		
		theModel.addAttribute("courses", courses);
		
		theModel.addAttribute("course", new Course());
		
		System.out.println("course");
		return "courses.jsp";
	}
	@RequestMapping("/CreateCourse")
	public String createCourse(Model theModel)
	{
	   
		Course	course=new Course();
		
	    theModel.addAttribute("course", course);
		System.out.println("coursecreate");
		return "CreateCourse.jsp";
	}
	@RequestMapping("/updateCourse")
	public String updateCourse(@ModelAttribute("course") Course course,Model theModel)
	{
	   
		if(course==null)
		{
				 course=new Course();
		}
		else{
		   System.out.println("course id"+course.getId());
            Course theCourse=tutorDao.getCourse(course);
            course=theCourse;
		}
	    theModel.addAttribute("course", course);
		System.out.println("coursecreate");
		return "CreateCourse.jsp";
	}
  
	@PostMapping("/saveCourse")
	public String SaveCourse(@ModelAttribute("course") Course course)
	{
	 
		tutorDao.saveCourse(course);
		
		return "redirect:/tutor/courses";
	}
	
	@RequestMapping("/deleteCourse")
	public String deleteCourse(@ModelAttribute("course") Course course,Model theModel)
	{
	   
		
		   System.out.println("course id"+course.getId());
           Course theCourse=tutorDao.getCourse(course);
           tutorDao.deleteCourse(theCourse);
		
	    
		System.out.println("coursedelete");
		return "redirect:/tutor/courses" ;
	}
	@RequestMapping("/taskDetails")
	public String taskDetails(@ModelAttribute("course") Course course, Model model)
	{
	   System.out.println(course.getId()+"   is the id");
	   Course theCourse=tutorDao.getCourse(course);	
	   List<tasks> task= theCourse.getTask();
	   
	   model.addAttribute("tasks",task);
	   
	   
	   model.addAttribute("course", course);
	   
	   model.addAttribute("task",new tasks());
	   
	   System.out.println(task);
	    
		return "task_details.jsp";
	}
	
	@RequestMapping("/CreateTask")
	public String createTask(@ModelAttribute("course") Course course,Model theModel)
	{
	   tasks task=new tasks();
	   Course theCourse=tutorDao.getCourse(course);
	   
	   task.setCourse(theCourse);
	   System.out.println("course id "+theCourse.getId());
	   long millis=System.currentTimeMillis();  
       Date on_date=new Date(millis); 
       task.setOn_date(on_date);
	   
	   
	   theModel.addAttribute("task", task);
	   
		System.out.println("create task"+course.getId());
		return "CreateTask.jsp";
	}
	@PostMapping("/saveTask")
	public String SaveTask(@ModelAttribute("task") tasks task,RedirectAttributes redirectAttributes)
	{

		System.out.println("create task   "+task.getCourse().getId());
		task.setCourse(tutorDao.getCourse(task.getCourse().getId()));
		tutorDao.saveTask(task);
		
		Course theCourse=tutorDao.getCourse(task.getCourse().getId());
		   
		redirectAttributes.addFlashAttribute("course",theCourse);
		 
		return "redirect:/tutor/taskDetails";
	}
	@PostMapping("/uploadMAT")
	public String handleFileUploadMat(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes,@ModelAttribute("task") tasks task) {

		//@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes
		System.out.println("task id is " + task.getId());
		tasks tasks=tutorDao.getTask(task);
		storageService.matupload(file,tasks);
		/* if(!file.isEmpty())
		{
			System.out.println(file.getContentType());
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename());
		} */
		// redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!"); 
		
		Course theCourse=tutorDao.getCourse(task.getCourse());
		
		   System.out.println("uploadmat"+theCourse.getId());
		   
		redirectAttributes.addFlashAttribute("course",theCourse);
		return "redirect:/tutor/taskDetails";
	}
	
	@RequestMapping("/materials")
	public String showMaterials(@ModelAttribute("course") Course course,Model theModel,@ModelAttribute("task") tasks task)
	{
		  
		   //Course theCourse=tutorDao.getCourse(course);
		   
		   /*  task.setCourse(theCourse);
		   System.out.println("course id "+theCourse.getId());
		   */
		tasks thetask = tutorDao.getTask(task);
		   System.out.println("task id "+task.getId());
		   
		   Course theCourse=tutorDao.getCourse(thetask.getCourse());
		   theModel.addAttribute("course",theCourse);
		   theModel.addAttribute("task", thetask);
		   
			//System.out.println("materials "+course.getId());
			
			theModel.addAttribute("files", storageService.loadAll("mat",task).map(path -> MvcUriComponentsBuilder
																		.fromMethodName(tutorController.class,"serveFileMAT", path.getFileName().toString())
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
		  
		  tasks thetask = tutorDao.getTask(task);
		   System.out.println("task id "+task.getId());
		   
		   
		   theModel.addAttribute("task", thetask);
		   Course theCourse=tutorDao.getCourse(thetask.getCourse());
		   theModel.addAttribute("course",theCourse);
			
			theModel.addAttribute("files", storageService.loadAll("sub",task).map(path -> MvcUriComponentsBuilder
														.fromMethodName(tutorController.class,"serveFileSUB", path.getFileName().toString())
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

		//@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes
		tasks tasks=tutorDao.getTask(task);
		storageService.subupload(file,tasks);
		// redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!"); 
		
		Course theCourse=tutorDao.getCourse(task.getCourse().getId());
		   
		redirectAttributes.addFlashAttribute("course",theCourse);
		return "redirect:/tutor/taskDetails";
	}
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping("/taskUpdate")
	public String createTask(@ModelAttribute("task") tasks task,Model theModel)
	{
	   tasks theTask=tutorDao.getTask(task);
	   if(task.getOn_date()==null)
       {
	        long millis=System.currentTimeMillis();  
	        Date on_date=new Date(millis); 
	        task.setOn_date(on_date);
       }
	   
	   System.out.println("task id "+task.getId());
	   
	   theModel.addAttribute("task",theTask);
	   
	    return "CreateTask.jsp";
	}
	
	
	@PostMapping("/taskDelete")
	public String deleteTask(@ModelAttribute("task") tasks task,RedirectAttributes redirectAttributes)
	{

		System.out.println("create task   "+task.getId());
		
		Course theCourse=tutorDao.getCourse(tutorDao.getTask(task).getCourse().getId());
		
		tutorDao.deleteTask(task);
		   
		redirectAttributes.addFlashAttribute("course",theCourse);
		 
		return "redirect:/tutor/taskDetails";
	}
	
	//@GetMapping("/AddUser")
	@GetMapping("/addCourseForStudent")
	public String addCourseForStudent(Model model)
	{
		model.addAttribute("courses",tutorDao.getCourses());
		
		return "addcousrseforstudent.jsp";
	}
	
	@PostMapping("/saveCouseforStudent")
	public String saveCouseforStudent(@RequestParam("c_id") int course_id,@RequestParam("s_name") String student_email)
	{
		studentDaoImpl.addCourseforStudent(student_email,course_id);
		
		System.out.println(student_email);
		System.out.println(course_id);
		
		return "redirect:/home";
	}
	
}
