package com.ovr.onlinevehicle.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovr.onlinevehicle.reservation.model.Locations;
import com.ovr.onlinevehicle.reservation.repo.LocationRepository;



@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	public List<Locations> getAllLocations() {
		List<Locations> locations = new ArrayList<>();
		locationRepository.findAll().forEach(locations::add);
		return locations;
	}

}
