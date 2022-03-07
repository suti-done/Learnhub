package com.Learnhub.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="student_name")
	private String name;
	
	@Column(name="student_email")
	private String email;
	
	@Column(name="student_number")
	private String phone_number;
	
	@ManyToMany
	@JoinTable(
	  name = "course_student", 
	  joinColumns = @JoinColumn(name = "student_id"), 
	  inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	public Student()
	{
		
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student(String name, String email, String phone_number) {
		
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", name=" + name + ", email=" + email + ", phone_number=" + phone_number + "]";
	}
	
	

}
