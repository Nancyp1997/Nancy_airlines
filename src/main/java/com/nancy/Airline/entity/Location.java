package com.nancy.Airline.entity;

public class Location {
	
	private String id;
	private int int_id;
	private int airport_int_id;
	private boolean active;
	private String name;
	private City city;
	private Special[] special;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInt_id() {
		return int_id;
	}
	public void setInt_id(int int_id) {
		this.int_id = int_id;
	}
	public int getAirport_int_id() {
		return airport_int_id;
	}
	public void setAirport_int_id(int airport_int_id) {
		this.airport_int_id = airport_int_id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Special[] getSpecial() {
		return special;
	}
	public void setSpecial(Special[] special) {
		this.special = special;
	}
	public Location(String id, int int_id, int airport_int_id, boolean active, String name, City city,
			Special[] special) {
		super();
		this.id = id;
		this.int_id = int_id;
		this.airport_int_id = airport_int_id;
		this.active = active;
		this.name = name;
		this.city = city;
		this.special = special;
	}
	public Location() {
		
	}

	
}
