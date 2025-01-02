package edu.cjc.sms.app.servicei;

import java.util.List;

import edu.cjc.sms.app.model.Student;

public interface StudentServiceI {

	List<Student> addStudent(Student s);
	List<Student> getAllStudents();
	List<Student> getBatchesStudent(String batchNumber);
	Student getStudent(int id);
	List<Student> updateStudentFees(int id, float amt);
	void removeStudent(int id);
	List<Student> paging(int pageNo, int i);

}
