package com.mall.meongnyang.client.mypage.dao;

import com.mall.meongnyang.client.member.vo.ClientCustomerVO;

public interface ClientMyinfoDAO {
	
	
	//�ڵ��� �ֱ�
	public void updateMyinfoPhone(ClientCustomerVO clientCustomerVO);
	
	
	//��й�ȣ ����
	public void updateMyinfoPassword(ClientCustomerVO clientCustomerVO);
	
	//ȸ��Ż��
	public void deleteMyinfoCustomer(ClientCustomerVO clientCustomerVO);
}
