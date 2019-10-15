package com.scattergather.demo;

public class InputPojo {
	
	private String name;
	private Integer age;
	
	public InputPojo(String namex, int agex) {
		this.name=namex;
		this.age=agex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "MyPojo [name=" + name + ", age=" + age + "]";
	}
}