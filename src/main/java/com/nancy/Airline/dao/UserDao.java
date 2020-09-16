package com.nancy.Airline.dao;

import java.util.List;

import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
	public void saveBooking(Booking b, String username);
	
	public String fetchPassword(String userName);
	
	public List<Booking> getMyTrips(String userName) ;
}
