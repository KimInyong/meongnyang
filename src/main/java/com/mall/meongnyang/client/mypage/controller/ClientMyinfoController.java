package com.mall.meongnyang.client.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.meongnyang.client.member.vo.ClientCustomerVO;
import com.mall.meongnyang.client.mypage.service.ClientDeleteMyinfoService;
import com.mall.meongnyang.client.mypage.service.ClientSelectMyinfoAddressService;

import com.mall.meongnyang.client.mypage.service.ClientUpdateMyinfoPasswordService;
import com.mall.meongnyang.client.mypage.service.ClientUpdateMyinfoPhoneService;
import com.mall.meongnyang.client.mypage.vo.ClientCmAddressVO;

@Controller
public class ClientMyinfoController {

	@Autowired
	private ClientUpdateMyinfoPhoneService clientUpdateMyinfoPhoneService;

	@Autowired
	private ClientUpdateMyinfoPasswordService clientUpdateMyinfoPasswordService;
	
	@Autowired
	private ClientDeleteMyinfoService clientDeleteMyinfoCustomerService;

	@Autowired
	private ClientSelectMyinfoAddressService clientSelectMyinfoAddressService;
	
	
	
	

	@RequestMapping(value = "/myinfo-update-phone.do", method = RequestMethod.POST)
	public String updateMyinfoPhoneProc(ClientCustomerVO clientCustomerVO, HttpSession session) {
		ClientCustomerVO tempVO = (ClientCustomerVO) session.getAttribute("customer");
		int no = tempVO.getCustomerTbNo();
		clientCustomerVO.setCustomerTbNo(no);
		
		StringBuffer phone = new StringBuffer(clientCustomerVO.getCustomerTbPhone());
		phone.insert(3, "-");
		phone.insert(8, "-");
		
		clientCustomerVO.setCustomerTbPhone(phone.toString());
		
		
		clientUpdateMyinfoPhoneService.updateMyinfoPhone(clientCustomerVO);

		return "mypage/myinfo";
	}

	
	@RequestMapping(value = "/myinfo-update-password.do", method = RequestMethod.POST)
	public String updateMyinfoPasswordProc(@RequestParam String newPassword1, @RequestParam String newPassword2,
		ClientCustomerVO clientCustomerVO, HttpSession session, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		ClientCustomerVO SessionVO = (ClientCustomerVO) session.getAttribute("customer");
		String no = SessionVO.getCustomerTbPassword();
		
		System.out.println(no + "��ȣȭ �� ��ȣ(DB)");
		System.out.println(clientCustomerVO.getCustomerTbPassword()+ " : DB���� ��ġ�ϴ� ����ڰ� ������");
		if (encoder.matches(clientCustomerVO.getCustomerTbPassword(), SessionVO.getCustomerTbPassword())) {
			if(newPassword1.equals(newPassword2)) {
			
			//���ο� ��ȣ ��ȣȭ ����	
			String securePw = encoder.encode(newPassword1);
			
			//���ο� �н����尪 ����
			clientCustomerVO.setCustomerTbPassword(securePw);
			System.out.println(newPassword1 + " : ���ο� ���");
			System.out.println(securePw + " : ��ȣȭ�� ���ο� ���");	
			int id = SessionVO.getCustomerTbNo();
			clientCustomerVO.setCustomerTbNo(id);
			clientUpdateMyinfoPasswordService.updateMyinfoPassword(clientCustomerVO);
			System.out.println(clientCustomerVO.getCustomerTbPassword());
			model.addAttribute("passwordsuccess", false);
			} else {
				model.addAttribute("passwordCheck", false);
				return "mypage/myinfo";
			}
		} else {
			model.addAttribute("realPasswordCheck", false);
			return "mypage/myinfo";
		}
		
		

		return "mypage/myinfo";
			
	}

	@RequestMapping(value = "/myinfo-delete-customer.do", method = RequestMethod.POST)
	public String deleteMyinfoCustomerProc(HttpSession session, ClientCustomerVO clientCustomerVO, Model model) {
		ClientCustomerVO tempVO = (ClientCustomerVO) session.getAttribute("customer");
		//���ǿ� �ִ� no��
		String password = tempVO.getCustomerTbPassword();
		//vo�� ����
		clientCustomerVO.setCustomerTbPassword(password);
		if(tempVO.getCustomerTbPassword().equals(clientCustomerVO.getCustomerTbPassword())) {
			
			clientDeleteMyinfoCustomerService.deleteMyinfo(clientCustomerVO);
			
		} else if(clientCustomerVO.getCustomerTbPassword() == null) {
			model.addAttribute("passwordDelete", false);
			return "mypage/myinfo";
		}
		return "mypage/myinfo";
	}
	
	
	
}
