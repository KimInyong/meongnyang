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
import com.mall.meongnyang.client.mypage.service.ClientSelectMyinfoService;
import com.mall.meongnyang.client.mypage.service.ClientUpdateMyinfoPasswordService;
import com.mall.meongnyang.client.mypage.service.ClientUpdateMyinfoPhoneService;
import com.mall.meongnyang.client.mypage.vo.ClientCmAddressVO;

@Controller
public class ClientMyinfoController {

	@Autowired
	private ClientSelectMyinfoService clientSelectMyinfoService;

	@Autowired
	private ClientUpdateMyinfoPhoneService clientUpdateMyinfoPhoneService;

	@Autowired
	private ClientUpdateMyinfoPasswordService clientUpdateMyinfoPasswordService;
	
	@Autowired
	private ClientDeleteMyinfoService clientDeleteMyinfoCustomerService;

	@RequestMapping(value = "/myinfo.do", method = RequestMethod.GET)
	public String myinfo(ClientCmAddressVO clientCmAddressVO, Model model) {
		ClientCmAddressVO tempVO = clientSelectMyinfoService.selectMyinfo(clientCmAddressVO);

		model.addAttribute("clientMyinfoVO", tempVO);

		return "mypage/myinfo";
	}

	@RequestMapping(value = "/myinfo-update-phone.do", method = RequestMethod.POST)
	public String updateMyinfoPhoneProc(ClientCustomerVO clientCustomerVO, HttpSession session) {
		ClientCustomerVO tempVO = (ClientCustomerVO) session.getAttribute("customer");
		int no = tempVO.getCustomerTbNo();
		clientCustomerVO.setCustomerTbNo(no);
		clientUpdateMyinfoPhoneService.updateMyinfoPhone(clientCustomerVO);

		return "mypage/myinfo";
	}

	//���� �����Բ� ���庸��
	@RequestMapping(value = "/myinfo-update-password.do", method = RequestMethod.POST)
	public String updateMyinfoPasswordProc(@RequestParam String newPassword1, @RequestParam String newPassword2,
			@RequestParam String customerTbPassword ,ClientCustomerVO clientCustomerVO, HttpSession session) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(customerTbPassword);
		ClientCustomerVO DBVO = clientUpdateMyinfoPasswordService.selectMyinfoPassword(clientCustomerVO);
		
		//DB�Դٰ��ٽ���
		
		if (encoder.matches(DBVO.getCustomerTbPassword(), clientCustomerVO.getCustomerTbPassword())) {
			if(newPassword1.equals(newPassword2)) {
			ClientCustomerVO tempVO = (ClientCustomerVO) session.getAttribute("customer");
			//���ǿ� �ִ� no��
			int no = tempVO.getCustomerTbNo();
			//vo�� ����
			clientCustomerVO.setCustomerTbNo(no);
			//���ο� �н����尪 ����
			clientCustomerVO.setCustomerTbPassword(newPassword1);
			clientUpdateMyinfoPasswordService.updateMyinfoPassword(clientCustomerVO);
			} else {
				
				return "mypage/myinfo";
			}
		} else {
			return "mypage/myinfo";
		}

		return "mypage/myinfo";
			
	}

	@RequestMapping(value = "/myinfo-delete-customer.do", method = RequestMethod.POST)
	public String deleteMyinfoCustomerProc(HttpSession session, ClientCustomerVO clientCustomerVO) {
		ClientCustomerVO tempVO = (ClientCustomerVO) session.getAttribute("customer");
		//���ǿ� �ִ� no��
		String password = tempVO.getCustomerTbPassword();
		//vo�� ����
		clientCustomerVO.setCustomerTbPassword(password);
		if(tempVO.getCustomerTbPassword().equals(clientCustomerVO.getCustomerTbPassword())) {
			
			clientDeleteMyinfoCustomerService.deleteMyinfo(clientCustomerVO);
			
		} else if(clientCustomerVO.getCustomerTbPassword() == null) {
			return "mypage/myinfo";
		}
		return "mypage/myinfo";
	}
	
	
	
}
