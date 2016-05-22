package com.cnt.sms.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.cnt.sms.dao.MemberDao;
import com.cnt.sms.vo.Member;

@Controller
public class MemberListController implements ServletContextAware {
	private MemberDao memberDao;
	private ServletContext servletContext;
	
	@RequestMapping(value = "/teamList.do", method = RequestMethod.GET)
	public ModelAndView getMemberList(HttpServletRequest request) throws Exception {
		Connection conn = (Connection) servletContext.getAttribute("conn");
		memberDao = new MemberDao();
		memberDao.setConnection(conn);
		
		ArrayList<Member> memberList = (ArrayList<Member>) memberDao.getMeberList();
		request.setAttribute("memberList", memberList);
		return new ModelAndView("team_List");
	}
	
	@RequestMapping(value = "/manualMatching.do", method = RequestMethod.POST)
	public ModelAndView requestManualMatching(HttpServletRequest request) throws Exception {
		Connection conn = (Connection) servletContext.getAttribute("conn");
		memberDao = new MemberDao();
		memberDao.setConnection(conn);
		
		int myNo = (Integer) request.getSession().getAttribute("id");
		int userNo = Integer.parseInt(request.getParameter("user_id"));
		
		memberDao.requestMatching(myNo, userNo);
		
		return new ModelAndView("redirect:/");
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
