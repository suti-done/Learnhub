package com.Learnhub.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Learnhub.entity.Authority;
import com.Learnhub.entity.Course;
import com.Learnhub.entity.Materials;
import com.Learnhub.entity.Student;
import com.Learnhub.entity.Submission;
import com.Learnhub.entity.Tutor;
import com.Learnhub.entity.UserHelper;
import com.Learnhub.entity.tasks;
import com.Learnhub.entity.user;

@Repository
public class studentDaoImpl implements studentDao {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	 private JpaUserDetailsService userService;
	
	
	 @Autowired
	 private UserRepository userRepository;;

	@Override
	@Transactional
	public List<Course> getCourses() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		
		String currentPrincipalName = authentication.getName();
		
		Session session=entityManager.unwrap(Session.class);
		
		Query<Student> query=session.createQuery("from Student where student_email=:n",Student.class);
		query.setParameter("n",currentPrincipalName); 
		
		Student student=query.getSingleResult();
		
		List<Course> courses= student.getCourses();
		
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
	public void saveSubmission(Submission submission) {

        Session session=entityManager.unwrap(Session.class);
		
		
		session.save(submission);

	}




	@Override
	@Transactional
	public void saveAuthority2(UserHelper userHelper) {
		Session session=entityManager.unwrap(Session.class);
		Supplier<UsernameNotFoundException> s = 
				 () -> new UsernameNotFoundException(
				 "Problem during authentication!"); 		
		
		user u = userRepository
				 .findUserByUsername(userHelper.getUsername()) 
				 .orElseThrow(s); 
		Authority authority =new Authority();
		authority.setName("ROLE_STUDENT");
		authority.setUser(u);
		
		//authorityService.addAuthority(authority);
		session.save(authority);
		System.out.println("addAuthority2");

	}
	@Override
	@Transactional
	public void updateUser(UserHelper userHelper) {

		Session session=entityManager.unwrap(Session.class);
		
		 System.out.println("update user");
		 
		 
		 user user=userService.getUser(userHelper.getUsername());
		 user.setUsername(userHelper.getUsername());
		 user.setPassword(userHelper.getPassword());
		
		userService.addUser(user);

	}
	@Override
	@Transactional
	public List<String> getMaterials(tasks task) {
		
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
	public List<String> getSubmissions(tasks task) {
		
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

	@Override
	@Transactional
	public void saveUser(UserHelper userHelper)  {
		 Session session=entityManager.unwrap(Session.class);
			
		 System.out.println("save user");
		 System.out.println(userHelper.getProfile()+"/");
		 if(userHelper.getProfile().equals("student"))
		 {
			 System.out.println(userHelper.getProfile());
			 Student student=new Student();
			 student.setName(userHelper.getName());
			 student.setEmail(userHelper.getUsername());
			 student.setPhone_number(userHelper.getPhone_number());
			 
			 session.save(student);
		 }
		 user user=new user();
		 user.setUsername(userHelper.getUsername());
		 user.setPassword(userHelper.getPassword());
		
		 
		userService.addUser(user);
		
		
		
	}

	@Override
	@Transactional
	public void addCourseforStudent(String student_email, int course_id) {
		
	    Session session=entityManager.unwrap(Session.class);
		 
		Query<Student> query=session.createQuery("from Student where email=:n",Student.class);
		query.setParameter("n",student_email); 
		
		Student theStudent=query.getSingleResult();
		for(Course course:theStudent.getCourses())
		{
			if(course.getId()==course_id)
			{
				return;
			}
		}
		Course course=getCourse(course_id);
		
		course.addStudent(theStudent);
		
	}
}
