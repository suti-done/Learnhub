package com.Learnhub.dao;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Learnhub.entity.Authority;
import com.Learnhub.entity.Course;
import com.Learnhub.entity.Materials;
import com.Learnhub.entity.Submission;
import com.Learnhub.entity.Tutor;
import com.Learnhub.entity.UserHelper;
import com.Learnhub.entity.tasks;
import com.Learnhub.entity.user;

@Repository
public class tutorDaoImpl implements tutorDao {

	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	 private JpaUserDetailsService userService;
	
	
	 @Autowired
	 private UserRepository userRepository;
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		
		String currentPrincipalName = authentication.getName();
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Tutor> query=session.createQuery("from Tutor where tutor_email=:n",Tutor.class);
		query.setParameter("n",currentPrincipalName); 
		
		Tutor tutor=query.getSingleResult();
		
		List<Course> courses= tutor.getCourses();
		
		return courses;
	}
	
	@Override
	@Transactional
	public Course getCourse(Course course) {
		
		
		
		Session session=entityManager.unwrap(Session.class);
		
		Course theCourse=session.get(Course.class, course.getId());
		
		return theCourse;
	}
	@Override
	@Transactional
	public Course getCourse(int id) {
		
		
		
		Session session=entityManager.unwrap(Session.class);
		
		Course theCourse=session.get(Course.class, id);
		
		return theCourse;
	}
	@Override
	@Transactional
	public tasks getTask(tasks task) {
		
		
		
		Session session=entityManager.unwrap(Session.class);
		
		tasks theTask=session.get(tasks.class, task.getId());
		
		return theTask;
	}
	@Override
	@Transactional
	public void saveCourse(Course course) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		
		String currentPrincipalName = authentication.getName();
		
        Session session=entityManager.unwrap(Session.class);
		
		Query<Tutor> query=session.createQuery("from Tutor where tutor_email=:n",Tutor.class);
		query.setParameter("n",currentPrincipalName); 
		
		Tutor tutor=query.getSingleResult();
		
		course.setTutor(tutor);
		
		System.out.println(currentPrincipalName);
		
		session.saveOrUpdate(course);
	
	}
	@Override
	@Transactional
	public void saveTask(tasks task) {
		
        Session session=entityManager.unwrap(Session.class);
		
		//Query<Course> query=session.createQuery("from Course where id=:n",Course.class);
		//query.setParameter("n",course.getId()); 
        //System.out.println(course.getId());
        long millis=System.currentTimeMillis();  
        Date on_date=new Date(millis); 
        task.setOn_date(on_date);
        
        if(task.getOn_date()==null)
        {
	        millis=System.currentTimeMillis();  
	         on_date=new Date(millis); 
	        task.setOn_date(on_date);
        }
		
        System.out.println("create task   "+task.getCourse().getId());
		session.saveOrUpdate(task);
	
	}
	
	@Override
	@Transactional
	public void saveMaterials(Materials materials) {
		
        Session session=entityManager.unwrap(Session.class);
		
		
		session.save(materials);
	
	}
	
	@Override
	@Transactional
	public void saveSubmission(Submission submission) {
		
        Session session=entityManager.unwrap(Session.class);
		
		
		session.save(submission);
	
	}

	@Override
	@Transactional
	public void deleteTask(tasks task) {
		
        Session session=entityManager.unwrap(Session.class);
		
		Query query=session.createQuery("delete from tasks where id=:n");
		query.setParameter("n",task.getId()); 
		
		
		query.executeUpdate();
		
		
	}

	@Override
	@Transactional
	public void deleteCourse(Course theCourse) {
		 Session session=entityManager.unwrap(Session.class);
			
			Query query=session.createQuery("delete from Course where id=:n");
			query.setParameter("n",theCourse.getId()); 
			
			
			query.executeUpdate();
		
	}
	
	@Override
	@Transactional
	public void saveUser(UserHelper userHelper)  {
		 Session session=entityManager.unwrap(Session.class);
			
		 System.out.println("save user");
		 System.out.println(userHelper.getProfile()+"/");
		 if(userHelper.getProfile().equals("tutor"))
		 {
			 System.out.println(userHelper.getProfile());
			 Tutor tutor=new Tutor();
			 tutor.setName(userHelper.getName());
			 tutor.setEmail(userHelper.getUsername());
			 tutor.setPhone_number(userHelper.getPhone_number());
			 
			 session.save(tutor);
		 }
		 user user=new user();
		 user.setUsername(userHelper.getUsername());
		 user.setPassword(userHelper.getPassword());
		
		 
		userService.addUser(user);
		
		
		
	}

	@Override
	@Transactional
	public void saveAuthority2(UserHelper userHelper) 
	{
		 Session session=entityManager.unwrap(Session.class);
		 Supplier<UsernameNotFoundException> s = 
				 () -> new UsernameNotFoundException(
				 "Problem during authentication!"); 		
		
		user u = userRepository
				 .findUserByUsername(userHelper.getUsername()) 
				 .orElseThrow(s); 
		Authority authority =new Authority();
		authority.setName("ROLE_TUTOR");
		authority.setUser(u);
		
		//authorityService.addAuthority(authority);
		session.save(authority);
		System.out.println("addAuthority2");
	}
	@Override
	@Transactional
	public List<String> getMaterials(tasks task) 
	{
		 Session session=entityManager.unwrap(Session.class);
		 Query<tasks> query=session.createQuery("from tasks where id=:n",tasks.class);
		 query.setParameter("n",task.getId()); 
			
		tasks task2=query.getSingleResult();
			
		List<Materials> mat= task2.getMaterials();
		List<String> names=new ArrayList<String>();
		for (Materials i : mat) {
			 names.add(i.getName());
        }
			
		return names;
	}
	@Override
	@Transactional
	public List<String> getSubmissions(tasks task) 
	{
		 Session session=entityManager.unwrap(Session.class);
		 Query<tasks> query=session.createQuery("from tasks where id=:n",tasks.class);
		 query.setParameter("n",task.getId()); 
			
		tasks task2=query.getSingleResult();
			
		List<Submission>sub= task2.getSubmission();
		List<String> names=new ArrayList<String>();
		for (Submission i : sub) {
			 names.add(i.getName());
        }
			
		return names;
	}
	
	
	
	
	
}
