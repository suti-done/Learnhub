package com.Learnhub.dao;

import java.util.List;


import com.Learnhub.entity.Course;
import com.Learnhub.entity.Materials;
import com.Learnhub.entity.Submission;
import com.Learnhub.entity.Tutor;
import com.Learnhub.entity.UserHelper;
import com.Learnhub.entity.tasks;

public interface tutorDao {
	
	List<Course> getCourses();

	void saveCourse(Course course);

	void saveTask(tasks task);

	Course getCourse(Course course);

	Course getCourse(int id);

	void saveMaterials(Materials materials);

	tasks getTask(tasks task);

	void saveSubmission(Submission submission);

	void deleteTask(tasks task);

	void deleteCourse(Course theCourse);

	void saveUser(UserHelper userHelper);

	void saveAuthority2(UserHelper userHelper);


	List<String> getMaterials(tasks task);

	List<String> getSubmissions(tasks task);

<<<<<<< HEAD
	Tutor getTutor(String email);

=======
>>>>>>> 5503d92d84afc243ddae5bd0381926f53eea9def


}
