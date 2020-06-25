package com.smart.ticketing.facereco.data;

public class Duration{

	private String text;

	private int value;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Duration{" + 
			"text = '" + text + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}