package com.Learnhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	private int id;
	
	@Column(name="name")
	private String name;

	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
	@JoinColumn(name="tutor_id")
	private Tutor tutor; 
	
	@ManyToMany
	@JoinTable(
	  name = "course_student", 
	  joinColumns = @JoinColumn(name = "course_id"), 
	  inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> student; 
	

	@OneToMany(mappedBy="course",orphanRemoval = true,cascade = CascadeType.ALL)
	private List<tasks> task;
	
	public Course()
	{
		
	}
	
	
	public List<Student> getStudent() {
		return student;
	}


	public void setStudent(List<Student> student) {
		this.student = student;
	}

    public void addStudent(Student theStudent)
    {
    	if(student==null)
    	{
    		student=new ArrayList<Student>();
    	}
    	student.add(theStudent);
    }
	public Course(String name, Tutor tutor) {
		
		this.name = name;
		//this.tutor_id = tutor_id;
		this.tutor = tutor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor_id) {
		this.tutor= tutor_id;
	}

	public List<tasks> getTask() {
		return task;
	}

	public void setTask(List<tasks> task) {
		this.task = task;
	}

	

}
