package com.smart.ticketing.facereco.data;

import java.util.List;

public class RowsItem{

	private List<ElementsItem> elements;

	public void setElements(List<ElementsItem> elements){
		this.elements = elements;
	}

	public List<ElementsItem> getElements(){
		return elements;
	}

	@Override
 	public String toString(){
		return 
			"RowsItem{" + 
			"elements = '" + elements + '\'' + 
			"}";
		}
}