package com.ovr.onlinevehicle.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovr.onlinevehicle.reservation.model.Locations;
import com.ovr.onlinevehicle.reservation.service.LocationService;



@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/all")
	public List<Locations> getAllLocations() {
		return locationService.getAllLocations();
	}

}
