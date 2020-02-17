package com.mall.meongnyang.admin.dashboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.meongnyang.admin.dashboard.service.AdminLogoutService;
import com.mall.meongnyang.admin.dashboard.service.AdminSelectLoginCheckService;
import com.mall.meongnyang.admin.dashboard.vo.AdminLoginVO;

@Controller
public class AdminLoginController {
	
	@Autowired
	private AdminLogoutService adminLogoutService;
	@Autowired
	private AdminSelectLoginCheckService adminSelectLoginCheckService;
	
	
	//단순한 로그인화면
	@RequestMapping(value = "/login.ado")
	public String login() {
		return "admin-page/login";
	}
	
	
	@RequestMapping(value = "/index.ado", method = RequestMethod.POST)
	public String loginProc(AdminLoginVO adminLoginVO, HttpSession session, Model model) {
		
		AdminLoginVO tempVO = adminSelectLoginCheckService.selectLoginCheck(adminLoginVO);
				
		if(tempVO!=null && (tempVO.getAdminsTbPassword()).equals(adminLoginVO.getAdminsTbPassword())) {
			
			session.setAttribute("adminId", tempVO.getAdminsTbId());
			
			return "index";
		//실패
		} else {
			model.addAttribute("loginCheckSubmit", false);
			return "admin-page/login";
		}
			
	}
	
	@RequestMapping(value = "/logout.ado", method = RequestMethod.GET)
	public String logoutProc(AdminLoginVO adminLoginVO, HttpSession session) {
		
		//session.invalidate();
		session.setAttribute("adminId", null);;
		
		return "admin-page/login";
	}
}
