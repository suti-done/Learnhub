package com.Learnhub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Learnhub.dao.UserRepository;
import com.Learnhub.dao.studentDaoImpl;
import com.Learnhub.dao.tutorDaoImpl;
import com.Learnhub.entity.UserHelper;
import com.Learnhub.entity.user;

@Controller
public class HomeController {
	

	@Autowired
	private tutorDaoImpl tutorDao;
	
	@Autowired
	private studentDaoImpl studentDao;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/")
	public String firstpage()
	{
		System.out.println("test");
		return "index.jsp";
	}
	
	@RequestMapping("/home")
	public String homepage()
	{
		System.out.println("test");
		return "home.jsp";
	}
	@RequestMapping("/login")
	public String homepage2()
	{
		System.out.println("test");
		return "home.jsp";
	}
	@RequestMapping("/register")
	public String register(Model model)
	{
		System.out.println("register");
		model.addAttribute("user",new UserHelper());
		
		return "Register.jsp";
	}
	
	@PostMapping("/saveUser")
	public String addUser(@ModelAttribute("user") UserHelper userHelper)
	{
		System.out.println(userHelper.getPassword());
		/*user user=new user();
		 user.setUsername(userHelper.getUsername());
		 user.setPassword(userHelper.getPassword());
		
		 //userRepo.save(user); */
		
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
		System.out.println("reset");
		model.addAttribute("user",new UserHelper());
		
		return "ResetPassword.jsp";
	}
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") UserHelper userHelper)
	{
		System.out.println(userHelper.getPassword());
		/*user user=new user();
		 user.setUsername(userHelper.getUsername());
		 user.setPassword(userHelper.getPassword());
		
		 //userRepo.save(user); */
		
		studentDao.updateUser(userHelper);
		return "redirect:/";
	}
}
