package com.mall.meongnyang.client.mypage.dao;

import com.mall.meongnyang.client.mypage.vo.ClientCmAddressVO;

public interface ClientMyinfoDAO {
	//���� ������
	public ClientCmAddressVO selectMyinfo(ClientCmAddressVO clientCmAddressVO);
	
	//���� ����
	public ClientCmAddressVO updateMyinfo(ClientCmAddressVO clientCmAddressVO);
}
