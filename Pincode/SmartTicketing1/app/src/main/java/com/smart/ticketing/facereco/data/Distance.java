package com.smart.ticketing.facereco.data;

public class Distance{

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
			"Distance{" + 
			"text = '" + text + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}