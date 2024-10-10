package com.human.dto;

public class HumanDto {
	private int id;
	private int age;
	private String name;
	private double height;
	private String birthday;

	public HumanDto() {
		// TODO Auto-generated constructor stub
	}

	public HumanDto(int id, int age, String name, double height, String birthday) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.height = height;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	
}
