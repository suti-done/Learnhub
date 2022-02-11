package com.Learnhub.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="task")
public class tasks {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="type")
	private String type; 
	
	@Column(name="on_date")
	private Date on_date;
	
	@Column(name="due_date")
	private Date due_date;
	
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,mappedBy="tasks")
	private List<Materials> materials;
	

	public List<Materials> getMaterials() {
		return materials;
	}


	public void setMaterials(List<Materials> materials) {
		this.materials = materials;
	}


	@OneToMany(mappedBy="task",orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Submission> submission;
	

	public List<Submission> getSubmission() {
		return submission;
	}


	public void setSubmissions(List<Submission> submission) {
		this.submission = submission;
	}


	public tasks()
	{
		
	}
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOn_date() {
		return on_date;
	}

	public void setOn_date(Date on_date) {
		this.on_date = on_date;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	@Override
	public String toString() {
		return "tasks [id=" + id + ", title=" + title + ", type=" + type + ", on_date=" + on_date + ", due_date="
				+ due_date + ", materials=" + materials + ", submission=" + submission + ", course=" + course + "]";
	}
	
	
	
	

}
