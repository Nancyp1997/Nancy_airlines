package com.nancy.Airline.entity;

public class City {
	
	private String id;
	private String name;
	private String code;
	private Country country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City(String id, String name, String code, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.country = country;
	}
	public City() {
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", code=" + code + ", country=" + country + "]";
	}
	

}
