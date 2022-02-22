package com.sol.pj.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO mDAO;
	
	// 로그인 페이지 이동
	@RequestMapping(value = "goto.login", method = RequestMethod.GET)
	public String gotoLogin() {
		return "member/Login";
	}
	
	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, Member m) {
		
		mDAO.login(m, req);
		mDAO.logincheck(req);
		
		return "home";
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, Member m) {
		
		mDAO.logout(req);
		mDAO.logincheck(req);
		
		return "home";
	}
	
	// 카카오 로그인
	@RequestMapping(value = "kakao.login", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam String code) {
		
		System.out.println(code);
		 String access_Token = mDAO.getReturnAccessToken(code);
		 HashMap<String, Object> userInfo = mDAO.getUserInfo(access_Token);
		 
		 System.out.println(userInfo.get("nickname"));
		 System.out.println(userInfo.get("email"));
		return "home";
	}
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "goto.Reg", method = RequestMethod.GET)
	public String gotoReg() {
		return "member/regMember";
	}
	
}
