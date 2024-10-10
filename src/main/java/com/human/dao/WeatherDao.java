package com.human.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.human.dto.WeatherDto;

public interface WeatherDao {
	public ArrayList<WeatherDto> listAll() throws Exception;
	public int insertCity(@Param("w_city")String w_city) throws Exception;
	public int delete(@Param("city")String city);

}
