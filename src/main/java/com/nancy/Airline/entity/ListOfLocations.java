package com.nancy.Airline.entity;

public class ListOfLocations {
	
	private Location[] locations;

	public Location[] getLocations() {
		return locations;
	}

	public void setLocations(Location[] locations) {
		this.locations = locations;
	}

	public ListOfLocations(Location[] locations) {
		
		this.locations = locations;
	}

	public ListOfLocations() {
	}
	
	
}
