package com.nancy.Airline.service;

import java.util.List;

import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.ListOfLocations;
import com.nancy.Airline.entity.Trip;

public interface PassengerService {
	

	
	
	
	
	
	
	//Add method to save the booking to trips table in db with current user logged in
	public void saveBooking(Booking b,String userName);
	
	
	public ListOfLocations getLocations() ;
	
	public List<Trip> getFlights(String from,String to, String date);

}
