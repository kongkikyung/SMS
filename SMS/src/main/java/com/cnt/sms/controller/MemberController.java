package com.cnt.sms.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cnt.sms.dao.MemberDao;
import com.cnt.sms.service.LoginService;
import com.cnt.sms.vo.Matching;
import com.cnt.sms.vo.Member;

@Controller
public class MemberController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired // 자동으로 객체 생성
	private ServletContext servletContext;
	private LoginService loginService;
	private MemberDao memberDao;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/signupPage.do")
	public ModelAndView getSignupPage(HttpServletRequest request) {
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value = "/myPage.do")
	public ModelAndView getMyPage(HttpServletRequest request) throws Exception {
		Connection conn = (Connection) servletContext.getAttribute("conn");
		memberDao = new MemberDao();
		memberDao.setConnection(conn);
		
		int myNo = (Integer) request.getSession().getAttribute("id");
		
		ArrayList<Matching> myMatchingList = (ArrayList<Matching>) memberDao.getMyMatchingList(myNo);
		request.setAttribute("myMatchingList", myMatchingList);
		
		ArrayList<Matching> yourMatchingList = (ArrayList<Matching>) memberDao.getYourMatchingList(myNo);
		request.setAttribute("yourMatchingList", yourMatchingList);
		
		return new ModelAndView("mypage");
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public ModelAndView signup(@RequestParam("id") String id,
			@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("teamName") String teamName, @RequestParam("teamMark") MultipartFile file)
			throws IllegalStateException, IOException {
		String teamMark = file.getOriginalFilename();
		String newMark = System.currentTimeMillis() + file.getSize() + teamMark;
		String path = servletContext.getRealPath("/resources/image");
		File f = new File(path + File.separator + newMark);
		file.transferTo(f);

		Connection conn = (Connection) servletContext.getAttribute("conn");
		memberDao = new MemberDao();
		memberDao.setConnection(conn);

		try {
			memberDao.signup(id, password, name, teamName, teamMark, newMark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/loginPage.do")
	public ModelAndView loginPage(HttpServletRequest request) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		Connection conn = (Connection) servletContext.getAttribute("conn");
		memberDao = new MemberDao();
		memberDao.setConnection(conn);

		String userID = request.getParameter("id");
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
			mv.setViewName("alert");
		} else {
			session.setMaxInactiveInterval(60 * 60);
			request.getSession().setAttribute("member", member);
			request.getSession().setAttribute("id", member.getNo());
			request.getSession().setAttribute("userName", member.getName());
			request.getSession().setAttribute("email", member.getEmail());
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}
	
}
