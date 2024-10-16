package com.weather.dto;

public class WeatherDto {
	private int w_id;
	private String w_city;
	public WeatherDto() {
		// TODO Auto-generated constructor stub
	}
	public WeatherDto(int w_id, String w_city) {
		super();
		this.w_id = w_id;
		this.w_city = w_city;
	}
	public int getW_id() {
		return w_id;
	}
	public void setW_id(int w_id) {
		this.w_id = w_id;
	}
	public String getCity() {
		return w_city;
	}
	public void setCity(String city) {
		this.w_city = city;
	}
}
