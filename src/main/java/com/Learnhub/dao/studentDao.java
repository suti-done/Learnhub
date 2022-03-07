package com.Learnhub.dao;

import java.util.List;


import com.Learnhub.entity.Course;
import com.Learnhub.entity.Materials;
import com.Learnhub.entity.Submission;
import com.Learnhub.entity.UserHelper;
import com.Learnhub.entity.tasks;

public interface studentDao {
	
	List<Course> getCourses();

	Course getCourse(Course course);

	Course getCourse(int id);

	tasks getTask(tasks task);

	void saveSubmission(Submission submission);

	void saveUser(UserHelper userHelper);

	void saveAuthority2(UserHelper userHelper);

	List<String> getMaterials(tasks task);

	List<String> getSubmissions(tasks task);

	void updateUser(UserHelper userHelper);

	void addCourseforStudent(String student_email, int course_id);

}
