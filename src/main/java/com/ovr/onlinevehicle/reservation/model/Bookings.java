package com.ovr.onlinevehicle.reservation.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long booking_id;
	private Date fromdate;
	private Date todate;
	private Integer booked_days;
	private Integer total_fare;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicle_id", nullable = false)
	private Vehicles vehicles;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owners_id", nullable = false)
	private Owners owners;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Bookings() {
		
	}

	public Bookings(Long booking_id, Date fromdate, Date todate, Integer booked_days, Integer total_fare,
			Vehicles vehicles, Owners owners, User user) {
		super();
		this.booking_id = booking_id;
		this.fromdate = fromdate;
		this.todate = todate;
		this.booked_days = booked_days;
		this.total_fare = total_fare;
		this.vehicles = vehicles;
		this.owners = owners;
		this.user = user;
	}

	public Long getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(Long booking_id) {
		this.booking_id = booking_id;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Integer getBooked_days() {
		return booked_days;
	}

	public void setBooked_days(Integer booked_days) {
		this.booked_days = booked_days;
	}

	public Integer getTotal_fare() {
		return total_fare;
	}

	public void setTotal_fare(Integer total_fare) {
		this.total_fare = total_fare;
	}

	public Vehicles getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	public Owners getOwners() {
		return owners;
	}

	public void setOwners(Owners owners) {
		this.owners = owners;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
