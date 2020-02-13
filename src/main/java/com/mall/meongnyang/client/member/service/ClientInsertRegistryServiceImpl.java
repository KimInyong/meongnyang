package com.mall.meongnyang.client.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mall.meongnyang.client.member.dao.ClientLoginDAO;
import com.mall.meongnyang.client.member.vo.ClientCustomerVO;

@Service("ClientInsertRegistry")
public class ClientInsertRegistryServiceImpl implements ClientInsertRegistryService {

	@Autowired
	private ClientLoginDAO clientLoginDAO;

	@Override
	public int insertRegistry(ClientCustomerVO clientCustomerVO) {
		BCryptPasswordEncoder encoder 
			= new BCryptPasswordEncoder();
		System.out.println("���� ��ȣ" + clientCustomerVO.getCustomerTbPassword());
		String securePw = encoder.encode(clientCustomerVO.getCustomerTbPassword());
		clientCustomerVO.setCustomerTbPassword(securePw);
		
		System.out.println("��ȣȭ �� " + securePw);
		
		int result = clientLoginDAO.insertRegistry(clientCustomerVO);
		return result;
		
	}

	
	
	

}
