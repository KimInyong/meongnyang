package com.mall.meongnyang.client.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientMyinfoController {

	//�ܼ� ������ �̵�
	@RequestMapping(value = "/myinfo.do")
	public String myinfo() {
		
		return "mypage/myinfo";
	}
}
