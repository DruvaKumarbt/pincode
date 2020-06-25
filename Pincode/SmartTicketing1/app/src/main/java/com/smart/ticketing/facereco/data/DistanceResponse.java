package com.smart.ticketing.facereco.data;

import java.util.List;

public class DistanceResponse{

	private List<String> destinationAddresses;

	private List<RowsItem> rows;

	private List<String> originAddresses;

	private String status;

	public void setDestinationAddresses(List<String> destinationAddresses){
		this.destinationAddresses = destinationAddresses;
	}

	public List<String> getDestinationAddresses(){
		return destinationAddresses;
	}

	public void setRows(List<RowsItem> rows){
		this.rows = rows;
	}

	public List<RowsItem> getRows(){
		return rows;
	}

	public void setOriginAddresses(List<String> originAddresses){
		this.originAddresses = originAddresses;
	}

	public List<String> getOriginAddresses(){
		return originAddresses;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DistanceResponse{" + 
			"destination_addresses = '" + destinationAddresses + '\'' + 
			",rows = '" + rows + '\'' + 
			",origin_addresses = '" + originAddresses + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}