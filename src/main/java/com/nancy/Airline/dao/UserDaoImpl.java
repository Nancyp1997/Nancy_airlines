package com.nancy.Airline.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.User;



@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}
	
	@Override
	public void saveBooking(Booking b, String username) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println(b.toString());
		
		
		currentSession.save(b);

	}

	@Override
	public String fetchPassword(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", userName);
		System.out.println("Query: "+theQuery);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
			
		} catch (Exception e) {
			theUser = null;
		}
		return theUser.getPassword();
	}

	@Override
	public List<Booking> getMyTrips(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		List<Booking> bookings = new ArrayList<>();
		try {
			
			 bookings = currentSession
					.createQuery("from Booking where userName=:uName")
					.setString("uName", userName).list();
			for(Booking b1: bookings) {
				System.out.println(b1.toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bookings;
	}

}
