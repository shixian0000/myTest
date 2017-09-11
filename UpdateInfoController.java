package com.oracle.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Student;
import com.oracle.ssm.service.InformationService;
import com.oracle.ssm.service.UpdateInfoService;

@Controller
public class UpdateInfoController {

	@Autowired
	private UpdateInfoService  updateInfoService;
	@RequestMapping("/xgxx")
	public ModelAndView informationStudent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		Student student = new Student();
		student.setSid((String)session.getAttribute("ssid"));
		if(request.getParameter("name")!=null&&request.getParameter("name")!=""){
			student.setName(request.getParameter("name"));
		}
		
		if(request.getParameter("sex")!=null&&request.getParameter("sex")!=""){
			student.setSex(request.getParameter("sex"));
		}
		if(request.getParameter("classid")!=null&&request.getParameter("classid")!=""){
			student.setClassid(request.getParameter("classid"));
		}
		if(request.getParameter("password")!=null&&request.getParameter("password")!=""){
			student.setPassword(request.getParameter("password"));
		}
		if(request.getParameter("tel")!=null&&request.getParameter("tel")!=""){
			student.setTel(request.getParameter("tel"));
		}
		if(request.getParameter("weixin")!=null&&request.getParameter("weixin")!=""){
			student.setWeixin(request.getParameter("weixin"));
		}
		
		boolean b = updateInfoService.updateInfo(student);
		ModelAndView mav = new ModelAndView();
		if(b){
			mav.setViewName("huanying");
		}else{
			mav.setViewName("updateinformation");
		}
		 return mav;  
	}
}
