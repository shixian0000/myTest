package com.oracle.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.service.RoleService;

@Controller
public class InitController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/init")
	public ModelAndView ShowRoleLogin() {
		List rlist = roleService.selectAllRole();
		ModelAndView mav = new ModelAndView();
		mav.addObject("rlist", rlist);
		mav.setViewName("login");
		return mav;

	}
}
