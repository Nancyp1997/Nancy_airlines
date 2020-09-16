package com.nancy.Airline.dao;

import com.nancy.Airline.entity.Booking;

public interface PassengerDao {
	public void saveBooking(Booking b, String username);
}
