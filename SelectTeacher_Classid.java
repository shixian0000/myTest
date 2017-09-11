package com.oracle.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Course;
import com.oracle.ssm.service.SelectCourse;
import com.oracle.ssm.service.SelectTeacherClassid;
import com.oracle.ssm.util.MyHttpSession;

@Controller
public class SelectTeacher_Classid {
	@Autowired
	private SelectTeacherClassid selectTeacherClassid;
	@Autowired
	private SelectCourse selectCourse;

	@RequestMapping("/selectclassid")
	public ModelAndView selectClass_id(String id, HttpServletRequest request) {
		// System.out.println("--------------");
		HttpSession session = MyHttpSession.session;
		String ssid = (String) session.getAttribute("ssid");

		List classlist = (List) selectTeacherClassid.SelectClassid(ssid);
		List courselist = (List) selectCourse.selectCourse();

		ModelAndView mav = new ModelAndView();
		mav.addObject("classlist", classlist);
		mav.addObject("courselist", courselist);
		mav.setViewName("classList");
		return mav;
	}
}
