package com.nancy.Airline.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nancy.Airline.entity.Booking;

@Repository
public class PassengerDaoImpl implements PassengerDao {
	
	//Need to autowire the entity manager
	@Autowired
	private EntityManager entityManager;


	@Override
	public void saveBooking(Booking b, String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println(b.toString());
		
		
		currentSession.save(b);

	}

}
