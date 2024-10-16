package com.weather.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.weather.dto.WeatherDto;

public interface WeatherDao {
	public ArrayList<WeatherDto> listAll() throws Exception;
	public int insertCity(@Param("w_city")String w_city) throws Exception;
	public int delete(@Param("wid")int wid);

}
