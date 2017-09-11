package com.oracle.ssm.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.ssm.po.CheckonKey;
import com.oracle.ssm.service.InsertCheckOn;
import com.oracle.ssm.util.MyHttpSession;

@Controller
public class InsertCheckStatus {
	@Autowired
	private InsertCheckOn insertCheckOn;

	@RequestMapping("/insertcheck")
	public ModelAndView insertCheckstatu(HttpServletRequest request) {

		HttpSession session = MyHttpSession.session;
		List<CheckonKey> list = new ArrayList<CheckonKey>();
		// Map<String, Object> map = new HashMap<String, Object>();
		String str = request.getParameter("str");
		String[] s1 = str.split(";");
		String cid = request.getParameter("cid");
		String tid = (String) session.getAttribute("ssid");
		String times = request.getParameter("times");

		int sum = Integer.parseInt(session.getAttribute("stusum").toString());
		for (String ss : s1) {
			System.out.println(ss);
		}
		for (int i = 0; i < sum; i++) {

			CheckonKey cok = new CheckonKey();
			cok.setCourseid(cid);
			String[] s2 = s1[i].split(",");
			cok.setSid(s2[0]);
			cok.setTid(tid);
			BigDecimal bd = new BigDecimal(s2[1]);
			cok.setStateid(bd);
			BigDecimal bd1 = new BigDecimal(times);
			cok.setTimes(bd1);

			/*
			 * map.put("courseid",cid ); String[] s2 = s1[i].split(",");
			 * map.put("sid", s2[0]); map.put("tid", tid); map.put("stateid",
			 * s2[1]); map.put("times", times); for(Map.Entry<String, Object>
			 * entry:map.entrySet()){
			 * System.out.println(entry.getKey()+" "+entry.getValue()); }
			 */
			list.add(cok);

		}
		boolean b = insertCheckOn.insertCheckStatus(list);
		if (b) {
			System.out.println(11111);
		} else {
			System.out.println("====");

		}

		return new ModelAndView();

		/*
		 * String cid = request.getParameter("cid"); String sid =
		 * request.getParameter("sid"); String tid =
		 * request.getParameter("tid"); String statsidstr =
		 * request.getParameter("statsid"); int stats =
		 * Integer.valueOf(statsidstr); String timestr =
		 * request.getParameter("times"); int times = Integer.valueOf(timestr);
		 * System.out.println(cid + "  " + sid + "  " + tid + "  " + stats +
		 * "  " + times); boolean i = insertCheckOn.insertCheckStatus(cid, sid,
		 * tid, stats, times); ModelAndView mav = new ModelAndView();
		 * mav.setViewName();
		 */

	}
}
