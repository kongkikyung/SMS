package com.cnt.sms.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cnt.sms.dao.MemberDao;
import com.cnt.sms.service.LoginService;
import com.cnt.sms.vo.Member;

@Controller
public class UserController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired // 자동으로 객체 생성
	private ServletContext servletContext;
	private LoginService loginService;
	private MemberDao memberDao;

	// private LoginService service;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/signupPage.do")
	public String signupPage(HttpServletRequest request) throws UnsupportedEncodingException {
		return "signup";
	}

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(@RequestParam("id") String id,
			// MultipartFile이 제공하는 메서드를 이용해서 업로드 데이터 접근
			@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("teamName") String teamName, @RequestParam("teamMark") MultipartFile file)
			throws IllegalStateException, IOException {
		String teamMark = file.getOriginalFilename();
		String newMark = System.currentTimeMillis() + file.getSize() + teamMark;
		String path = servletContext.getRealPath("/resources/image");
		System.out.println("이미지 경로 : " + path);
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
		return "redirect:/";
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
		System.out.println("아이디 : " + userID);
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
			request.getSession().setAttribute("userName", member.getName());
			request.getSession().setAttribute("id", member.getEmail());
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
