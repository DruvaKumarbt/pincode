package com.smart.ticketing.facereco.data;

public class ElementsItem{

	private Duration duration;

	private Distance distance;

	private DurationInTraffic durationInTraffic;

	private String status;

	public void setDuration(Duration duration){
		this.duration = duration;
	}

	public Duration getDuration(){
		return duration;
	}

	public void setDistance(Distance distance){
		this.distance = distance;
	}

	public Distance getDistance(){
		return distance;
	}

	public void setDurationInTraffic(DurationInTraffic durationInTraffic){
		this.durationInTraffic = durationInTraffic;
	}

	public DurationInTraffic getDurationInTraffic(){
		return durationInTraffic;
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
			"ElementsItem{" + 
			"duration = '" + duration + '\'' + 
			",distance = '" + distance + '\'' + 
			",duration_in_traffic = '" + durationInTraffic + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}