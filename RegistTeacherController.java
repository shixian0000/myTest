package com.oracle.ssm.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.ClassTeacherKey;
import com.oracle.ssm.po.Student;
import com.oracle.ssm.po.TRoleKey;
import com.oracle.ssm.po.Teacher;
import com.oracle.ssm.service.RegistTeacher;

@Controller
public class RegistTeacherController {

	@Autowired
	private RegistTeacher registTeacher;
	@RequestMapping("/zcjs")
	public ModelAndView zhuCe(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Teacher teacher = new Teacher();
		ClassTeacherKey classTeacherKey = new ClassTeacherKey();
		TRoleKey tRolekey = new TRoleKey();
		teacher.setTid(request.getParameter("tid"));
		teacher.setName(request.getParameter("name"));
		teacher.setSex(request.getParameter("sex"));
		teacher.setTel(request.getParameter("tel"));
		teacher.setWeixin(request.getParameter("weixin"));
		teacher.setPassword(request.getParameter("password"));
		classTeacherKey.setClassid(request.getParameter("classid"));
		classTeacherKey.setTid(request.getParameter("tid"));
		
		tRolekey.setTid(request.getParameter("tid"));
		tRolekey.setRoleid(new BigDecimal(2));
		boolean b = registTeacher.registTeacher(teacher, classTeacherKey,tRolekey);
		ModelAndView mav = new ModelAndView();
		if(b){
			mav.setViewName("huanying");
		}else{
			mav.setViewName("error");
		}
        return mav;
	}
	
}
