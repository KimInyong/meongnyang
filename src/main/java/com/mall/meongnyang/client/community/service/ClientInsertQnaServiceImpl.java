package com.mall.meongnyang.client.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.meongnyang.admin.shopping.vo.AdminQnaVO;
import com.mall.meongnyang.client.community.dao.ClientQnaDAO;

@Service("ClientInsertQnaService")
public class ClientInsertQnaServiceImpl implements ClientInsertQnaService {

	@Autowired
	private ClientQnaDAO clientQnaDAO;

	@Override
	public void insertNewQna(AdminQnaVO adminQnaVO)
	{
		clientQnaDAO.insertNewQna(adminQnaVO);
	}

	@Override
	public void insertQnaReply(AdminQnaVO adminQnaVO)
	{
		clientQnaDAO.insertQnaReply(adminQnaVO);
		clientQnaDAO.updateStep(adminQnaVO);
		clientQnaDAO.updateStepDepth(adminQnaVO);
	}
}