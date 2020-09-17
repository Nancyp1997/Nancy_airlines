package com.nancy.Airline.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nancy.Airline.entity.Booking;
import com.nancy.Airline.entity.ListOfLocations;
import com.nancy.Airline.entity.Location;
import com.nancy.Airline.entity.Route;
import com.nancy.Airline.entity.Trip;
import com.nancy.Airline.entity.User;
import com.nancy.Airline.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService us;
	
	private Map<String,String> cityCode;
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/navbar")
	public String navbar() {return "navbar";}

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
	
    //Updated every search
	private List<Trip> privateListOfTrips;
	
	@RequestMapping("/passengers/search")
	public String searchPage(Model theModel) {
		this.cityCode= new HashMap<String,String>();
		ListOfLocations locations = us.getLocations();		
		Location[] loc = locations.getLocations();
		theModel.addAttribute("loc", loc);
		theModel.addAttribute("from", "");
		theModel.addAttribute("to", "");
		theModel.addAttribute("date", null);
		System.out.println("Locations array size: "+loc.length);
		for(Location lo : loc) {
			//Updating cityCode map
			this.cityCode.put(lo.getCity().getCode(),lo.getCity().getName());
		}
		
		return "searchflights";
	}
	
	//On click of submit after selecting from , to , date this mapping will trigger API call and return flights list.
	@PostMapping("/passengers/searchResult")
	public String listFlights(@ModelAttribute("from") String from, @ModelAttribute("to") String to,@ModelAttribute("date") String date, Model theModel) {
		//For every search refresh the listOfTrips
		this.privateListOfTrips = new ArrayList<>();
		List<Trip> list = us.getFlights(from,to,date);
		
		//Update with the latest fetched trips
		this.privateListOfTrips= list;
		
		//Populating routes with the cityNames corresponding to the city codes.
		for(Trip t: list) {
			Route[] r = t.getRoutes();
			for(Route r1: r) {
				System.out.println(r1.toString()+" : "+this.cityCode.get(r1.getFrom())+" -> "+this.cityCode.get(r1.getTo()));
				r1.setFrom(this.cityCode.get(r1.getFrom()));
				r1.setTo(this.cityCode.get(r1.getTo()));
			}
			t.setRoutes(r);
		}
		
		//Adding the trips list to model to load it in the html
		if(list!=null) {
			theModel.addAttribute("trips", list);
			return "flights";
		}
		return "redirect:/passengers/search";	
	}
	
	@RequestMapping("/passengers/changePassword")
	public String book(@ModelAttribute("oldPassword") String oldPassword,
			@ModelAttribute("newPassword") String newPassword,@ModelAttribute("confirmNewPassword") String confirmNewPassword) {
		//Check if new Password and confirm Password are equal
		if(!(newPassword.equals(confirmNewPassword))) {
			return "redirect:/myprofile";
		}else {
			System.out.println("Passwords are equal");
		}
		
		//Bcrypt the oldPassword text
		String oldPasswordHash = BCrypt.hashpw(oldPassword, BCrypt.gensalt());
		
		//Fetch the entry from DB 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		String dbPassword = us.fetchPassword(loggedInUser);
		System.out.println("Fetched password from DB: "+dbPassword);
		
		//Check if both are equal
		boolean crtPwd = oldPasswordHash.equals(dbPassword);
		System.out.println("Passwords equal: "+crtPwd);
		
		//Bcrypt the new Password and save Password
		//String newPasswordHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		//System.out.println("New password Hash is "+newPasswordHash);
		
		User theUser = us.findByUserName(loggedInUser);
		
		//Save new Password to db
		us.updatePassword(theUser, newPassword);
		 
		return "redirect:/";
	}
	
	
	@RequestMapping("/passengers/book")
	public String book(@ModelAttribute("atime") String atime,
			@ModelAttribute("dtime") String dtime,			
			@ModelAttribute("fromCity") String fromCity,
			@ModelAttribute("toCity") String toCity,
			@ModelAttribute("cost") String cost,
			Model theModel) {
		
		Booking b = new Booking();
		theModel.addAttribute("booking", b);
		b.setaTime(atime);
		b.setCost(cost);
		b.setTo(toCity);
		b.setFrom(fromCity);
		b.setdTime(dtime);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		System.out.println("Loggedin User: "+loggedInUser);
		b.setUserName(loggedInUser);
		
		theModel.addAttribute("atime", atime);
		theModel.addAttribute("dtime", dtime);
		theModel.addAttribute("fromCity", fromCity);
		theModel.addAttribute("toCity", toCity);
		theModel.addAttribute("cost", cost);
		
		return "book";
	}
	
	@RequestMapping("/passengers/confirmBooking")
	public String confirmBooking(@ModelAttribute("booking") Booking booking ,Model theModel) {
		
		
		//Add the booking to database
		us.saveBooking(booking,booking.getUserName());
		
		System.out.println(theModel.containsAttribute("booking")+booking.getFrom()+"->"+booking.getTo());
		return "redirect:/";
	}
	
	@RequestMapping("/passengers/mytrips")
	public String getMyTrips(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		System.out.println("Loggedin User: "+loggedInUser);
		
		List<Booking> myTrips = us.getMyTrips(loggedInUser);
		theModel.addAttribute("mytrips", myTrips);
		
		for(Booking b: myTrips) {
			System.out.println(b.toString());
		}
		
		//Get a fragment of code like navbar.html which could be added inside the mytrips collapsable cell
		return "mytrips";
	}
	
	
	
	@GetMapping("/myprofile")
	public String myProfile(Model theModel) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		System.out.println("Loggedin User: "+loggedInUser);
		
		List<Booking> myTrips = us.getMyTrips(loggedInUser);
		
		if(myTrips.size()==0) {
			System.out.println("Trips are empty");
			theModel.addAttribute("tripsSize", 0);
		}else {
			theModel.addAttribute("mytrips", myTrips);
		}
		for(Booking b: myTrips) {
			System.out.println(b.toString());
		}
		return "myProfile";
	}
	
	@RequestMapping("/offers")
	public String offers(Model theModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		System.out.println("Loggedin User: "+loggedInUser);
		theModel.addAttribute("user",loggedInUser);
		System.out.println("Fetching personalised offers");
		return "offers";
	}
	
}





/*
@RequestMapping("showMyLoginPage")
public String loginPage() {return "fancy-login";}


@RequestMapping("/passengers/list")
public String list(Model theModel) {
	List<User> lis = ps.findAll();
	theModel.addAttribute("passengerlist", lis);
	return "list";
}

@RequestMapping("/passengers/updateForm")
public String update(@RequestParam("id") int theID,Model theModel) {
	User p = ps.findById(theID);
	theModel.addAttribute("passenger", p);
	return "passengerForm";
}

@RequestMapping("/passengers/createForm")
public String createUser(Model theModel) {
	User p = new User();
	theModel.addAttribute("passenger", p);
	return "passengerForm";
}

@RequestMapping("/passengers/save")
public String saveUser(@ModelAttribute("passenger") User p ,Model theModel) {
	ps.save(p);
	return "redirect:/passengers/list";
}*/
