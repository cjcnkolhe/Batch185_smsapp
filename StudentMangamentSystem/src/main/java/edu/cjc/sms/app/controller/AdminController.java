package edu.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Controller
public class AdminController {
	int a;
	@Autowired
	StudentServiceI ssi;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String u,@RequestParam("password") String p,Model m) {
		if(u.equals("ADMIN")  && p.equals("ADMIN"))
		{
			m.addAttribute("data",ssi.getAllStudents());
			return "adminscreen";
		}else {
			return "login";
		}
	}
	
	@RequestMapping("/enroll_student")
	public String addStudent(@ModelAttribute Student s,Model m) {
		  List<Student> list=ssi.addStudent(s);
		  m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String saerchStudent(@RequestParam("batchNumber") String batchNumber,Model m) {
		
		  List<Student> list=ssi.getBatchesStudent(batchNumber); 
		  
		    if(list.size()>0) {
		    	m.addAttribute("data", list);
		    	return "adminscreen";
		    }else {
		    	List<Student> l=ssi.getAllStudents();
		    	m.addAttribute("data",l);
		    	m.addAttribute("message","No Record available for that batch   "+batchNumber);
		    	return "adminscreen";
		    }
	
	}
	
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("id") int id,Model m) {
		Student s=ssi.getStudent(id);
		m.addAttribute("st", s);
		return "fees";
	}
	
	@RequestMapping("/payfees")
	public String PayFees(@RequestParam("studentid") int id,@RequestParam("ammount") float amt,Model m) {
	
		List<Student> list=ssi.updateStudentFees(id,amt);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	
	@RequestMapping("/remove")
	public String removeDelete(@RequestParam("id") int id,Model m) {
		
		       ssi.removeStudent(id);
		
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/paging")
	public String paging(@RequestParam("pageNo") int pageNo,Model m) {
		

		List<Student> list=ssi.paging(pageNo,2);
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	
	
	
	
}
