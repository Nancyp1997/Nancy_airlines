package com.nancy.Airline.entity;

public class Route {
	
	@Override
	public String toString() {
		return "Route [from=" + from + ", to=" + to + ", id=" + id + "]";
	}

	private String from;
	
	private String to;
	
	private int id;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Route(String from, String to, int id) {
		this.from = from;
		this.to = to;
		this.id = id;
	}

	public Route() {
	}
	
	

}
