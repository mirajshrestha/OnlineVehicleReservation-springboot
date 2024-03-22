package com.ovr.onlinevehicle.reservation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Locations;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.BookingRepository;
import com.ovr.onlinevehicle.reservation.repo.VehicleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	public void registerVehicle(Vehicles vehicles) {
		vehicleRepository.save(vehicles);

	}

	public List<Vehicles> getOwnedVehicles(Long ownerid) {
		return vehicleRepository.findByOwners_id(ownerid);

	}

	public List<Vehicles> searchVehicles(String location, Date pickUpDate, Date returnDate) {

		return vehicleRepository.findAvailableVehicles(location, pickUpDate, returnDate);
	}

	public Vehicles getVehicleById(Long vehicleId) {
		// TODO Auto-generated method stub
		return vehicleRepository.findById(vehicleId).orElse(null);
	}

	public String saveImage(MultipartFile image) throws IOException{
		String uploadDir = "C:\\uploads";
		
		Path uploadPath = Path.of(uploadDir);
		System.out.println(uploadPath.toString());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        return uniqueFileName;
	}

	public boolean deleteVehicle(Long vehicleId) {
		Optional<Vehicles> optionalVehicle = vehicleRepository.findById(vehicleId);
		if(optionalVehicle.isPresent()) {
			Vehicles vehicle = optionalVehicle.get();
			vehicleRepository.delete(vehicle);
			return true;
			
		}
		return false;
		
	}

	public List<Vehicles> getAllVehicles() {
		List<Vehicles> cars = new ArrayList<>();
		vehicleRepository.findAll().forEach(cars::add);
		return cars;
	}

	public List<Object[]> getVehiclesWithLocations() {
	    return vehicleRepository.findVehiclesWithLocations();
	}

	public void updateVehicle(Vehicles updatedVehicle) {
        // Assuming that the repository method save() can be used for both updating and saving new entities
        vehicleRepository.save(updatedVehicle);
    }

	public void updateFeatureStatus(Long vehicleId, String featureStatus) {
        Optional<Vehicles> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalVehicle.isPresent()) {
            Vehicles vehicle = optionalVehicle.get();
            vehicle.setFeature(featureStatus);
            vehicleRepository.save(vehicle);
        } else {
            // Handle the case where the vehicle with the given ID is not found
            throw new EntityNotFoundException("Vehicle not found with ID: " + vehicleId);
        }
    }
	


}
