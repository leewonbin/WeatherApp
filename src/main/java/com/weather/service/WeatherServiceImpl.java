package com.weather.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.dao.WeatherDao;
import com.weather.dto.WeatherDto;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private SqlSession session;

	@Override
	public ArrayList<WeatherDto> listAll() throws Exception {
		WeatherDao dao = session.getMapper(WeatherDao.class);
		return dao.listAll();
	}

	@Override
	public int insertCity(String w_city) throws Exception {
		WeatherDao dao = session.getMapper(WeatherDao.class);
		return dao.insertCity(w_city);
	}

	@Override
	public int delete(int wid) throws Exception {
		WeatherDao dao = session.getMapper(WeatherDao.class);
		return dao.delete(wid);
		
	}
}
