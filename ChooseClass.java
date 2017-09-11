package com.oracle.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.service.ClassCountStudent;
import com.oracle.ssm.service.SelectAllStudent;
import com.oracle.ssm.service.SelectCourse;
import com.oracle.ssm.util.MyHttpSession;


@Controller
public class ChooseClass {

	@Autowired
	private SelectAllStudent selectAllStudent;
	
	@Autowired
	private ClassCountStudent classCountStudent;

	@RequestMapping("/222")
	public ModelAndView chooseClass(HttpServletRequest request) {
		HttpSession session = MyHttpSession.session;
		String courseid = request.getParameter("courseid");
		session.setAttribute("courseid", courseid);
		String id = request.getParameter("classid");
		String  times = request.getParameter("times");
		session.setAttribute("times", times);
		
		List studentlist = selectAllStudent.selectClassStudent(id);
		int sum = classCountStudent.getAllStudentCount(id);
		session.setAttribute("stusum", sum);
		ModelAndView mav = new ModelAndView();
		mav.addObject("studentlist", studentlist);

		mav.setViewName("checkstudent");
		return mav;
	}
}
