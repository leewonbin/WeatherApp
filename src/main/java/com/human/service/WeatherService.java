package com.human.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.human.dto.WeatherDto;

public interface WeatherService {
	public ArrayList<WeatherDto> listAll() throws Exception;
	public int insertCity(@Param("w_city")String w_city) throws Exception;
	public int delete(@Param("city")String city) throws Exception;

}
