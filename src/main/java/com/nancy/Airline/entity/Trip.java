package com.nancy.Airline.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Trip {
	
	private String id;
	
	private String dTimeUTC;
	
	private String aTimeUTC;
	
	private String  flyDuration;
	
	private String  flyFrom;
	
	private String flyTo;
	
	private Route[] routes;
	
	private String[] airlines;
	
	private int technicalStops;
	
	private int price;
	
	private Map availability;
	
	private String bookingToken;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getdTimeUTC() {
		return dTimeUTC;
	}

	public void setdTimeUTC(String dTimeUTC) {
		this.dTimeUTC = dTimeUTC;
	}

	public String getaTimeUTC() {
		return aTimeUTC;
	}

	public void setaTimeUTC(String aTimeUTC) {
		this.aTimeUTC = aTimeUTC;
	}

	public String getFlyDuration() {
		return flyDuration;
	}

	public void setFlyDuration(String flyDuration) {
		this.flyDuration = flyDuration.substring(1,flyDuration.length()-1);
	}

	public String getFlyFrom() {
		return flyFrom;
	}

	public void setFlyFrom(String flyFrom) {
		
		System.out.println(flyFrom);
		if(flyFrom!=null) {
		this.flyFrom = flyFrom.substring(1, flyFrom.length()-1);
		}
	}

	public String getFlyTo() {
		return flyTo;
	}

	public void setFlyTo(String flyTo) {
		if(flyTo!=null) {
		this.flyTo = flyTo.substring(1, flyTo.length()-1);
		}
	}

	public Route[] getRoutes() {
		return routes;
	}

	public void setRoutes(Route[] routes) {
		this.routes = routes;
	}

	public String[] getAirlines() {
		return airlines;
	}

	public void setAirlines(String[] airlines) {
		this.airlines = airlines;
	}

	public int getTechnicalStops() {
		return technicalStops;
	}

	public void setTechnicalStops(int technicalStops) {
		this.technicalStops = technicalStops;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Map getAvailability() {
		return availability;
	}

	public void setAvailability(Map availability) {
		this.availability = availability;
	}

	public String getBookingToken() {
		return bookingToken;
	}

	public void setBookingToken(String bookingToken) {
		this.bookingToken = bookingToken;
	}
	public String routeString() {
		StringBuilder sb = new StringBuilder();
		for(Route r: this.routes) {
			sb.append(" "+r.getFrom()+":"+r.getTo()+" \n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public Trip(String id, String dTimeUTC, String aTimeUTC, String flyDuration, String flyFrom, String flyTo, Route[] routes,
			String[] airlines, int technicalStops, int price, Map availability, String bookingToken) {
		this.id = id;
		this.dTimeUTC = dTimeUTC;
		this.aTimeUTC = aTimeUTC;
		this.flyDuration = flyDuration;
		this.flyFrom = flyFrom;
		this.flyTo = flyTo;
		this.routes = routes;
		this.airlines = airlines;
		this.technicalStops = technicalStops;
		this.price = price;
		this.availability = availability;
		this.bookingToken = bookingToken;
	}

	public Trip() {
	}
	
	

}
