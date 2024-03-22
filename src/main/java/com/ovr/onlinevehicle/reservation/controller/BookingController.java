package com.ovr.onlinevehicle.reservation.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovr.onlinevehicle.reservation.config.BookingRequest;
import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.BookingRepository;
import com.ovr.onlinevehicle.reservation.repo.OwnerRepository;
import com.ovr.onlinevehicle.reservation.repo.UserRepository;
import com.ovr.onlinevehicle.reservation.repo.VehicleRepository;
import com.ovr.onlinevehicle.reservation.service.BookingService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/confirm")
	public ResponseEntity<Bookings> confirmBooking(@RequestBody BookingRequest bookingRequest) {
		System.out.println("Received Request: " + bookingRequest.toString());

		System.out.println("From Date in BookingRequest: " + bookingRequest.getFromDate());
		System.out.println("To Date in BookingRequest: " + bookingRequest.getToDate());

		Bookings bookings = bookingRequest.toBooking(vehicleRepository, ownerRepository, userRepository);
		System.out.println("Created Bookings Object: " + bookings);
		bookings.setFromdate(bookingRequest.getFromDate());
		System.out.println("fromdate in bookings: " + bookings.getFromdate());
		System.out.println("todate in bookings: " + bookings.getTodate());

		Bookings confirmedBookings = bookingService.confirmBooking(bookings);
		System.out.println("Confirmed Bookings Object: " + confirmedBookings);

		return ResponseEntity.ok(confirmedBookings);

	}

	@GetMapping("/all")
	public List<Bookings> getAllBookings() {
		return bookingService.getAllBookings();
	}
	@PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        // You need to implement the logic to cancel the booking with the given bookingId
        // For example, you can update the booking status in the database
        // and perform any other necessary operations
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully");
    }
	

	

}
