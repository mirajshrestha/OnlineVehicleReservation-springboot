package com.ovr.onlinevehicle.reservation.config;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.OwnerRepository;
import com.ovr.onlinevehicle.reservation.repo.UserRepository;
import com.ovr.onlinevehicle.reservation.repo.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;

public class BookingRequest {
	private Long vehicle_id;
	private Long owners_id;
	private Long user_id;
	private Date fromDate;
	private Date toDate;
	private Integer booked_days;
	private Integer total_fare;

	public BookingRequest() {

	}

	public BookingRequest(Long vehicle_id, Long owners_id, Long user_id, Date fromDate, Date toDate,
			Integer booked_days, Integer total_fare) {
		super();
		System.out.println("vehicle_id"+vehicle_id);
		this.vehicle_id = vehicle_id;
		this.owners_id = owners_id;
		this.user_id = user_id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.booked_days = booked_days;
		this.total_fare = total_fare;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public Long getOwners_id() {
		return owners_id;
	}

	public void setOwners_id(Long owners_id) {
		this.owners_id = owners_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

	public Bookings toBooking(VehicleRepository vehicleRepository, OwnerRepository ownerRepository,
			UserRepository userRepository) {
		System.out.println("vehicle_id"+vehicle_id);
		System.out.println("this_vehicle_id"+this.vehicle_id);
		Bookings bookings = new Bookings();
		bookings.setFromdate(fromDate);
		bookings.setTodate(toDate);
		bookings.setBooked_days(booked_days);
		bookings.setTotal_fare(total_fare);

		Optional<Vehicles> vehicleOptional = vehicleRepository.findById(this.vehicle_id);
		if (vehicleOptional.isPresent()) {
			bookings.setVehicles(vehicleOptional.get());
		} else {
			// Handle the case when the vehicle is not found, you can throw an exception or
			// handle it as per your requirement.
			throw new EntityNotFoundException("Vehicle with ID " + vehicle_id + " not found.");
		}
		
		Optional<Owners> ownersOptional = ownerRepository.findById(this.owners_id);
		if (ownersOptional.isPresent()) {
			bookings.setOwners(ownersOptional.get());
		} else {
			// Handle the case when the owners are not found, you can throw an exception or
			// handle it as per your requirement.
			throw new EntityNotFoundException("Owners with ID " + owners_id + " not found.");
		}

		Optional<User> userOptional = userRepository.findById(this.user_id);
		if (userOptional.isPresent()) {
			bookings.setUser(userOptional.get());
		} else {
			// Handle the case when the user is not found, you can throw an exception or
			// handle it as per your requirement.
			throw new EntityNotFoundException("User with ID " + user_id + " not found.");
		}

		return bookings;
	}

}
