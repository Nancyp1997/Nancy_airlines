package com.nancy.Airline.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;// in play 2.3
import com.nancy.Airline.dao.PassengerDao;
import com.nancy.Airline.dao.UserDao;
import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.ListOfLocations;
import com.nancy.Airline.entity.Location;
import com.nancy.Airline.entity.Route;
import com.nancy.Airline.entity.Trip;
@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private PassengerDao pDao;
	
	//Injecting the dependency from app config for rest template
	private RestTemplate restTemplate;
	
	@Value("${location.rest.url}")
	private String locationRestUrl;
	
	@Value("${flights.query.rest.url}")
	private String flightsSearchUrl;
	
	
	@Autowired
	public PassengerServiceImpl(RestTemplate theRestTemplate,@Value("${location.rest.url}") String theUrl,
			@Value("${flights.query.rest.url}") String theUrl1) {
		restTemplate = theRestTemplate;
		locationRestUrl = theUrl;
		flightsSearchUrl = theUrl1;
	}
	


	
	@Override
	public ListOfLocations getLocations() {
	
	// make REST call
	ResponseEntity<ListOfLocations> responseEntity =
				restTemplate.exchange(locationRestUrl,HttpMethod.GET,null,
						new ParameterizedTypeReference<ListOfLocations>() {});
	ListOfLocations locations = responseEntity.getBody();
	Location[] loc = locations.getLocations();
	System.out.println(loc.length);
	return locations;
	}

	@Override
	public List<Trip> getFlights(String from,String to,String date) {
		
		String finalRestURL = this.flightsSearchUrl+"?flyFrom="+from+"&to="+to
				+"&dateFrom="+date+"&dateTo="+date+"&partner=picky";
		System.out.println(finalRestURL);
		
		
		// make REST call
		//ResponseEntity<ListOfTrips> responseEntity =restTemplate.exchange(finalRestURL,HttpMethod.GET,null,ListOfTrips.class);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(finalRestURL, String.class);
		String responseBody = response.getBody();
		List<Trip> tripsList=new ArrayList<Trip>();
		ObjectMapper objMapper = new ObjectMapper();
		
		try {
			JsonNode jsonNode = objMapper.readTree(responseBody);
			
			Iterator<JsonNode> itr = jsonNode.get("data").elements();
			while(itr.hasNext()) {
				JsonNode node = itr.next();
				Trip t = new Trip();
				String booking_token = node.get("booking_token").toString();
				String flightTripID = node.get("id").toString();
				String dTimeUTC = node.get("dTimeUTC").toString();
				String aTimeUTC = node.get("aTimeUTC").toString();
				String fly_duration = node.get("fly_duration").toString();
				String fromCityCode = node.get("flyFrom").toString();
				String toCityCode = node.get("cityTo").toString();
				String priceStr = node.get("price").toString();
				Iterator<JsonNode> routeNodes = node.get("routes").elements();
				int routes = node.get("routes").size();
				Route[] tempRoutes = new Route[routes];
				int i=0;
				while(routeNodes.hasNext()) {
					JsonNode temp = routeNodes.next();
					String[] listRoutetemp = objMapper.readValue(temp.toString(), new TypeReference<String[]>(){});
					Route tempRoute = new Route();
					tempRoute.setFrom(listRoutetemp[0]);
					tempRoute.setTo(listRoutetemp[1]);
					tempRoute.setId(i+1);
					tempRoutes[i]=tempRoute;
					i++;
				}
				t.setRoutes(tempRoutes);
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
				Date date1 = new Date(Long.parseLong(dTimeUTC)*1000);
				Date date2  = new Date(Long.parseLong(aTimeUTC)*1000);
				t.setPrice(Integer.parseInt(priceStr));
				t.setdTimeUTC(sf.format(date1));
				t.setaTimeUTC(sf.format(date2));
				t.setFlyDuration(fly_duration);
				t.setFlyFrom(fromCityCode);
				t.setFlyTo(toCityCode);
				t.setBookingToken(booking_token);
				t.setId(flightTripID);
				tripsList.add(t);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return tripsList;
	}




	@Override
	public void saveBooking(Booking b,String userName) {
		pDao.saveBooking(b, userName);
		
	}

}
