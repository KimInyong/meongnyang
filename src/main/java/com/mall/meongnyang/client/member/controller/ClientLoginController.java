package com.mall.meongnyang.client.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.meongnyang.client.member.service.ClientLogoutService;
import com.mall.meongnyang.client.member.service.ClientSelectLoginService;
import com.mall.meongnyang.client.member.vo.ClientCustomerVO;

@Controller
public class ClientLoginController {
	
	@Autowired
	private ClientSelectLoginService clientSelectLoginService;
	
	//�ʿ���� �α׾ƿ���
	@Autowired
	private ClientLogoutService clientLogoutService;
	
	
	
	
	
	//�α���ó��
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginProc(ClientCustomerVO clientCustomerVO,  HttpSession session, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		ClientCustomerVO tempVO = clientSelectLoginService.selectLoginCheck(clientCustomerVO);
		
		if(tempVO!=null && (encoder.matches(clientCustomerVO.getCustomerTbPassword(), tempVO.getCustomerTbPassword()))) {
			session.setAttribute("customer", tempVO);
			model.addAttribute("loginCheckSubmit", true);
			return "redirect:index.do";
		} else {
			model.addAttribute("loginCheckSubmit", false);
			return "redirect:index.do";
		}
	}
	
	//�α׾ƿ�ó��
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutProc(ClientCustomerVO clientCustomerVO, HttpSession session) {
		
		//session.invalidate();
		session.setAttribute("customer", null);
		System.out.println(session.getAttribute("clientId")+"��������");
		return "index";
	}
	
	
	
	
}
