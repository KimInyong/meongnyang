package com.mall.meongnyang.client.member.dao;

import com.mall.meongnyang.client.member.vo.ClientCustomerVO;
import com.mall.meongnyang.client.shopping.vo.ClientOrderVO;

public interface ClientLoginDAO {
	public ClientCustomerVO selectLoginCheck(ClientCustomerVO clientCustomerVO);
	
	public ClientCustomerVO logout(ClientCustomerVO clientCustomerVO);
	
	public int insertRegistry(ClientCustomerVO clientCustomerVO);
	//email
	public void updateState(ClientCustomerVO clientCustomerVO);
	
	public void insertKakao(ClientCustomerVO clientCustomerVO); //카카오아이디집어넣기 
	
	public void updateLastLogin(ClientCustomerVO clientCustomerVO);
	
	public void insertNaver(ClientCustomerVO clientCustomerVO); //네이버아이디집어넣기 

	public void updatePointUse(ClientOrderVO clientOrderVO);
	public void updatePointRollback(ClientOrderVO clientOrderVO);
	public void updatePointPlus(ClientOrderVO clientOrderVO);

	public void insertGoogleCustomer(ClientCustomerVO clientCustomerVO);
}
