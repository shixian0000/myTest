package com.oracle.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Student;
import com.oracle.ssm.service.StudentService;
import com.oracle.ssm.util.MyHttpSession;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;

	@RequestMapping("/selectone")
	public ModelAndView SelectOneStudent(String id, HttpServletRequest request) {
		String sid = request.getParameter("1234");
		HttpSession session =MyHttpSession.session;
		Student stu = service.selectOneStudent(sid);
		//System.out.println(stu.toString());
		//stu.getUrl();
		ModelAndView mav = new ModelAndView();
		mav.addObject("classList", session.getAttribute("student"));
		mav.setViewName("onestudent");
		return mav;
	}
}
