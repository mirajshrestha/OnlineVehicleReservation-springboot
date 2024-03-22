package com.ovr.onlinevehicle.reservation.config;

import java.sql.Date;

public class SearchRequest {
	private String location;
	private Date pickUpDate;
	private Date returnDate;
	
	public SearchRequest() {
		
	}

	public SearchRequest(String location, Date pickUpDate, Date returnDate) {
		super();
		this.location = location;
		this.pickUpDate = pickUpDate;
		this.returnDate = returnDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
	
	
}
