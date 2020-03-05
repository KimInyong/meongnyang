package com.mall.meongnyang.admin.member.dao;

import com.mall.meongnyang.admin.member.vo.AdminMemberVO;
import com.mall.meongnyang.client.member.vo.ClientCustomerVO;

import java.util.List;


public interface AdminMemberDAO {

	public void updateMember(AdminMemberVO adminMemberVO);
	public List<AdminMemberVO> selectNoticeList(AdminMemberVO adminMemberVO);
	public List<ClientCustomerVO> selectMailAgreeCustomerList(ClientCustomerVO clientCustomerVO);
	
}
