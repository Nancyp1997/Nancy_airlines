package com.nancy.Airline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="trips")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", from=" + from + ", to=" + to + ", aTime=" + aTime + ", dTime=" + dTime
				+ ", cost=" + cost + ", userName=" + userName + "]";
	}
	@Column(name = "fromcity")
	private String from;
	
	@Column(name = "tocity")
	private String to;
	
	@Column(name = "atime")
	private String aTime;
	
	@Column(name = "dtime")
	private String dTime;
	
	@Column(name = "price")
	private String cost;
	
	@Column(name="username")
	private String userName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	public String getaTime() {
		return aTime;
	}
	public void setaTime(String aTime) {
		
		this.aTime = aTime;
	}
	public String getdTime() {
		return dTime;
	}
	public void setdTime(String dTime) {
		this.dTime = dTime;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public Booking(int id, String from, String to, String aTime, String dTime, String cost, String userName) {
		
		this.id = id;
		this.from = from;
		this.to = to;
		this.aTime = aTime;
		this.dTime = dTime;
		this.cost = cost;
		this.userName = userName;
	}
	public Booking() {
	
		// TODO Auto-generated constructor stub
	}
	
	

}
