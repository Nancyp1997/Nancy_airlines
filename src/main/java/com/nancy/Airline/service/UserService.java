package com.nancy.Airline.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.ListOfLocations;
import com.nancy.Airline.entity.Trip;
import com.nancy.Airline.entity.User;
import com.nancy.Airline.user.CrmUser;




public interface UserService extends UserDetailsService {
	

	public User findByUserName(String userName);
	public String fetchPassword(String userName);

	public void save(CrmUser crmUser);
public ListOfLocations getLocations() ;
	
	public List<Trip> getFlights(String from,String to, String date);
	
	public void saveBooking(Booking booking,String userName);
	
	public void updatePassword(User theUser,String newPassword);
	
	public List<Booking> getMyTrips(String userName);

}
