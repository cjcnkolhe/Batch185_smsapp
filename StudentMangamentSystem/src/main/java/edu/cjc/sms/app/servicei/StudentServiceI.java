package edu.cjc.sms.app.servicei;

import java.util.List;

import edu.cjc.sms.app.model.Student;

public interface StudentServiceI {

	List<Student> addStudent(Student s);
	List<Student> getAllStudents();

}
