package com.Learnhub.dao;

import java.util.List;


import com.Learnhub.entity.Course;
import com.Learnhub.entity.Materials;
<<<<<<< HEAD
import com.Learnhub.entity.Student;
=======
>>>>>>> 5503d92d84afc243ddae5bd0381926f53eea9def
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

<<<<<<< HEAD
	Student getStudent(String name);

=======
>>>>>>> 5503d92d84afc243ddae5bd0381926f53eea9def
}
