package com.weather.dao;

import java.util.ArrayList;

import com.weather.dto.HumanDto;

public interface HumanDao {
	public ArrayList<HumanDto> selectAll() throws Exception;
	public HumanDto selectOne(int id) throws Exception;
	public int insertHuman(HumanDto dto) throws Exception;
	public int updateHuman(HumanDto dto) throws Exception;
	public int deleteHuman(int id) throws Exception;

}
