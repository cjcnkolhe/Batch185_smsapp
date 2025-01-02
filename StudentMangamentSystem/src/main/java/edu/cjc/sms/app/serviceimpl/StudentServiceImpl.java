package edu.cjc.sms.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.stereotype.Service;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repositary.StudentRepo;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Service
public class StudentServiceImpl implements StudentServiceI {

	@Autowired
	StudentRepo sr;

	@Override
	public List<Student> addStudent(Student s) {
		sr.save(s);
		return sr.findAll();
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return sr.findAll();
	}

	@Override
	public List<Student> getBatchesStudent(String batchNumber) {

		return sr.findAllByBatchNumber(batchNumber);
	}

	@Override
	public Student getStudent(int id) {

		return sr.findById(id).get();
	}

	@Override
	public List<Student> updateStudentFees(int id, float amt) {

		Optional<Student> op = sr.findById(id);
		if (op.isPresent()) {
			Student s = op.get();
			s.setFeesPaid(s.getFeesPaid() + amt);
			sr.save(s);
		}

		return sr.findAll();
	}

	@Override
	public void removeStudent(int id) {

		sr.deleteById(id);
	}

	@Override
	public List<Student> paging(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("studentFullName").ascending());
		List<Student> list = sr.findAll(pageable).getContent();

		return list;
	}

}
