package com.cnt.sms.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnt.sms.dao.MemberDao;
import com.cnt.sms.service.LoginService;
import com.cnt.sms.vo.Member;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	@Autowired	//자동으로 객체 생성
	private ServletContext context;
	private LoginService loginService;
	
	//private LoginService service;
	
	@RequestMapping(value = "/loginPage.do")
	public String loginPage(HttpServletRequest request) throws UnsupportedEncodingException {
		return "login";
	}
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
		Connection conn = (Connection) context.getAttribute("conn");
		MemberDao memberDao = new MemberDao();
		memberDao.setConnection(conn);
		
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("id");
		System.out.println("아이디 : " +userID);
		String userPassword = request.getParameter("password");
		
		Member member = null;
		try {
			member = memberDao.login(userID, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("로그인 요청 : " + userID);
		if (member == null) {
				return "alert";
		} else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			request.getSession().setAttribute("userSession", member);
			request.getSession().setAttribute("userName", member.getName());
			request.getSession().setAttribute("id", member.getEmail());
			return "redirect:/";
		}
	}
}
