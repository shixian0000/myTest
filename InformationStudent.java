package com.oracle.ssm.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Student;
import com.oracle.ssm.service.InformationService;

@Controller
public class InformationStudent {
	@Autowired
	private InformationService informationService;
	
	@RequestMapping("/xxtj")
	public ModelAndView informationStudent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		Student student = new Student();
		student.setSid((String)session.getAttribute("zhuceid"));
		
		student.setName(request.getParameter("name"));
		System.out.println(student.getName());
		student.setSex(request.getParameter("sex"));
		//student.setBirthday(request.getParameter("birthday"));
		student.setClassid(request.getParameter("classid"));
		student.setTel(request.getParameter("tel"));
		student.setWeixin(request.getParameter("weixin"));
		boolean b = informationService.updateInformation(student);
		ModelAndView mav = new ModelAndView();
		if(b){
			mav.setViewName("uploatphoto");
		}else{
			mav.setViewName("login");
		}
		 return mav;  
	}
	
}
