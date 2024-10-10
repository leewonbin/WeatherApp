package com.human.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.dao.HumanDao;
import com.human.dto.HumanDto;

@Service
public class HumanServiceImpl implements HumanService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<HumanDto> selectAll() throws Exception {
		HumanDao dao = sqlSession.getMapper(HumanDao.class);
		return dao.selectAll();
	}

	@Override
	public HumanDto selectOne(int id) throws Exception {
		HumanDao dao = sqlSession.getMapper(HumanDao.class);
		return dao.selectOne(id);
	}

	@Override
	public int updateHuman(HumanDto dto) throws Exception {
		HumanDao dao = sqlSession.getMapper(HumanDao.class);
		return dao.updateHuman(dto);
	}

	@Override
	public int deleteHuman(int id) throws Exception {
		HumanDao dao = sqlSession.getMapper(HumanDao.class);
		return dao.deleteHuman(id);
	}

	@Override
	public int insertHuman(HumanDto dto) throws Exception {
		HumanDao dao = sqlSession.getMapper(HumanDao.class);
		return dao.insertHuman(dto);
	}

}
