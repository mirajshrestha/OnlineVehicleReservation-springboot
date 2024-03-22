package com.ovr.onlinevehicle.reservation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovr.onlinevehicle.reservation.config.BookingRequest;
import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.BookingRepository;
import com.ovr.onlinevehicle.reservation.repo.OwnerRepository;
import com.ovr.onlinevehicle.reservation.repo.UserRepository;
import com.ovr.onlinevehicle.reservation.repo.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Bookings confirmBooking(Bookings bookings) {
//		Vehicles vehicles = vehicleRepository.findById(bookingRequest.getVehicle_id()).orElseThrow(
//				() -> new EntityNotFoundException("Vehicle not found with ID: " + bookingRequest.getVehicle_id()));
//		Owners owners = ownerRepository.findById(bookingRequest.getOwners_id()).orElseThrow(
//				() -> new EntityNotFoundException("Owner not found with ID: " + bookingRequest.getOwners_id()));
//		User user = userRepository.findById(bookingRequest.getUser_id())
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + bookingRequest.getUser_id()));
//		
//		Bookings bookings = new Bookings();
//		bookings.setVehicles(vehicles);
//		bookings.setOwners(owners);
//		bookings.setUser(user);
//		bookings.setFromdate(bookingRequest.getFromDate());
//		bookings.setTodate(bookingRequest.getToDate());
//		bookings.setBooked_days(bookingRequest.getBooked_days());
//		bookings.setTotal_fare(bookingRequest.getTotal_fare());
		
		return bookingRepository.save(bookings);
	}

	public List<Bookings> getAllBookings() {
		List<Bookings> books = new ArrayList<>();
		bookingRepository.findAll().forEach(books::add);
		return books;
	}

	public void cancelBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
		
	}

	

	


	

}
