package com.Learnhub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Learnhub.dao.studentDaoImpl;
import com.Learnhub.dao.tutorDaoImpl;
import com.Learnhub.entity.Student;
import com.Learnhub.entity.Tutor;
import com.Learnhub.entity.UserHelper;

@Controller
public class HomeController {
	

	@Autowired
	private tutorDaoImpl tutorDao;
	
	@Autowired
	private studentDaoImpl studentDao;

	
	@RequestMapping("/")
	public String firstpage()
	{
		System.out.println("test");
		return "index.jsp";
	}
	
	@RequestMapping("/home")
	public String homepage(Model model)
	{
		System.out.println("test");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object[] roles=authentication.getAuthorities().toArray();
		String role;
		Tutor tutor;
		for(Object theRole: roles)
		{
			role=theRole.toString();
			Student student;
			if(role.equals("ROLE_TUTOR"))
			{
				tutor=tutorDao.getTutor(authentication.getName());
				model.addAttribute("tutor",tutor);
				break;
			}
			if(role.equals("ROLE_STUDENT"))
			{
				student=studentDao.getStudent(authentication.getName());
				model.addAttribute("student",student);
				break;
			}
			
		}
        
		return "home.jsp";
	}
	@RequestMapping("/login")
	public String homepage2()
	{
		return "home.jsp";
	}
	@RequestMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("user",new UserHelper());
		
		return "Register.jsp";
	}
	
	@PostMapping("/saveUser")
	public String addUser(@ModelAttribute("user") UserHelper userHelper)
	{
		
		
		if(userHelper.getProfile().equals("tutor"))
		{

			tutorDao.saveUser(userHelper);
			tutorDao.saveAuthority2(userHelper);
		}
		else
		{
			studentDao.saveUser(userHelper);
			studentDao.saveAuthority2(userHelper);
		}
		
		
		return "redirect:/login";
	}
	@RequestMapping("/resetPassword")
	public String resetPassword(Model model)
	{
		model.addAttribute("user",new UserHelper());
		
		
		return "ResetPassword.jsp";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") UserHelper userHelper)
	{
		studentDao.updateUser(userHelper);
		return "redirect:/";
	}
}
