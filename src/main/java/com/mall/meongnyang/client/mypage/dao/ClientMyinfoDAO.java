package com.mall.meongnyang.client.mypage.dao;

import com.mall.meongnyang.client.member.vo.ClientCustomerVO;
import com.mall.meongnyang.client.mypage.vo.ClientCmAddressVO;

public interface ClientMyinfoDAO {
	//���� ������
	public ClientCmAddressVO selectMyinfo(ClientCmAddressVO clientCmAddressVO);
	
	//�ڵ��� �ֱ�
	public void updateMyinfoPhone(ClientCustomerVO clientCustomerVO);
	
	//��й�ȣ ����
	public void updateMyinfoPassword(ClientCustomerVO clientCustomerVO);
	
	
}
