package com.oracle.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Student;
import com.oracle.ssm.service.StudentRegist;

@Controller
public class StudentRegistController {
	@Autowired
	private StudentRegist StudentRegist;
	@RequestMapping("/studentzhuce")
	public ModelAndView zhuCe(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Student student = new Student();
		String sid = request.getParameter("sid");
		String spas1 = request.getParameter("spas1");
		String spas2 = request.getParameter("spas2");
		System.out.println(sid+spas1+spas2);
		session.setAttribute("zhuceid", sid);
		if (spas1.equals(spas2)) {
			student.setSid(sid);
			student.setPassword(spas2);
			StudentRegist.registStudent(student);
			return new ModelAndView("information");
		} else
			return new ModelAndView("error");
	}
}
