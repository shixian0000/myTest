package com.oracle.ssm.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.Role;
import com.oracle.ssm.service.RoleService;

@Controller
@SessionAttributes(value={"rlist"})
public class RoleController {

	@Autowired
	private RoleService roleService;
	@RequestMapping(value="/init",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView initRole(){
	    List<Role> rlist = roleService.selectAllRole();
		return new ModelAndView("login","rlist",rlist);
	}
	
}
