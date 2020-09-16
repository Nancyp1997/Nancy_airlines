package com.nancy.Airline.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfTrips {
	private String searchId;
	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	private Trip[] trips;

	public ListOfTrips() {
	
		// TODO Auto-generated constructor stub
	}

	public ListOfTrips(Trip[] trips,String searchId) {
	
		this.trips = trips;
		this.searchId=searchId;
	}

	public Trip[] getTrips() {
		return trips;
	}

	public void setTrips(Trip[] trips) {
		this.trips = trips;
	}

}
