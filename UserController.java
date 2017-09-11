package com.oracle.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.service.UserService;
import com.oracle.ssm.util.MyHttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpSession session, String id, String password,
			HttpServletRequest request) {
		// System.out.println("controller");
		MyHttpSession.session = session;
		String ids = request.getParameter("userid");
		String pas = request.getParameter("password1");
		int ro = Integer.parseInt(request.getParameter("t1"))+1;
		//System.out.println(ro);
		Boolean b = userService.login(ids, pas, ro);
		ModelAndView mav = new ModelAndView();
		if (b) {
			mav.setViewName("index");
			List m = (List) session.getAttribute("mlist");
			mav.addObject("m", m);
		} else {
			mav.addObject("message", "’ÀªßªÚ√‹¬Î¥ÌŒÛ");
			mav.setViewName("error");
		}

		return mav;

	}
}
