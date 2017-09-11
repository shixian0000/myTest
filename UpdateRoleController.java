package com.oracle.ssm.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.TRoleKey;
import com.oracle.ssm.service.UpdateRole;

@Controller
public class UpdateRoleController {

	@Autowired
	private UpdateRole updateRole;
	@RequestMapping("/qxxg")
	public ModelAndView informationStudent(HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		TRoleKey tRoleKey = new TRoleKey();
		tRoleKey.setTid(request.getParameter("tid"));
		if(request.getParameter("role")!=null&&request.getParameter("role")!=""){
			tRoleKey.setRoleid(new BigDecimal(Integer.parseInt(request.getParameter("role"))));
		}
		boolean b = updateRole.updateRole(tRoleKey);
		ModelAndView mav = new ModelAndView();
		if(b){
			mav.setViewName("huanying");
		}else{
			mav.setViewName("updaterole");
		}
		 return mav;  
	}
}
