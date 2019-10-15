package com.scattergather.demo;

public class OutputPojo{
	
	public OutputPojo(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyResult [value=" + value + "]";
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}