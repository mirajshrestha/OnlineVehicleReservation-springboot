package com.ovr.onlinevehicle.reservation.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ovr.onlinevehicle.reservation.config.SearchRequest;
import com.ovr.onlinevehicle.reservation.model.Locations;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.VehicleRepository;
import com.ovr.onlinevehicle.reservation.service.VehicleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/add")
    public ResponseEntity<String> registerVehicle(@RequestPart("image") MultipartFile image,
                                                  @RequestPart("vehicle") Vehicles vehicles, HttpServletRequest request) throws IOException {

        Long ownerId = (Long) request.getSession().getAttribute("ownerId");
        if (ownerId == null) {
            return ResponseEntity.badRequest().body("OwnerId not found");
        }

        Owners owners = new Owners();
        owners.setOwners_id(ownerId);
        vehicles.setOwners(owners);

        System.out.println("Data " + vehicles);

        String imagePath = vehicleService.saveImage(image);
        vehicles.setCarimg(imagePath);

        vehicleService.registerVehicle(vehicles);
        return ResponseEntity.ok("Vehicle registration Successful");

    }

    @GetMapping("/owner")
    public ResponseEntity<List<Vehicles>> getOwnedVehicles(HttpSession session) {
        Long ownerId = (Long) session.getAttribute("ownerId");
        List<Vehicles> vehicles = vehicleService.getOwnedVehicles(ownerId);

        return ResponseEntity.ok(vehicles);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicles>> searchVehicles(@RequestParam String location, @RequestParam Date pickUpDate,
                                                         @RequestParam Date returnDate) {
        List<Vehicles> searchResults = vehicleService.searchVehicles(location, pickUpDate, returnDate);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicles> getVehicleDetails(@PathVariable Long vehicleId) {
        Vehicles vehicles = vehicleService.getVehicleById(vehicleId);
        if (vehicles == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/all")
    public List<Vehicles> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("vehicleId") Long vehicleId) {
        boolean deleted = vehicleService.deleteVehicle(vehicleId);
        if (deleted) {
            return ResponseEntity.ok("Vehicle deleted succesfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete vehicle");
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Object[]>> getVehiclesWithLocations() {
        List<Object[]> results = vehicleService.getVehiclesWithLocations();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Object[]>> getVehiclesWithCategories() {
        List<Object[]> results = vehicleService.getVehiclesWithCategories();
        return ResponseEntity.ok(results);
    }

    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<String> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicles updatedVehicle,
                                                HttpSession session) {
        // Check if the owner is authenticated
        if (session != null && session.getAttribute("ownerId") != null) {
            Long ownerId = (Long) session.getAttribute("ownerId");

            // Fetch the existing vehicle from the database
            Vehicles existingVehicle = vehicleService.getVehicleById(vehicleId);

            // Check if the vehicle exists and belongs to the authenticated owner
            if (existingVehicle != null && existingVehicle.getOwners().getOwners_id().equals(ownerId)) {
                // Update the vehicle details
                existingVehicle.setManufacturer(updatedVehicle.getManufacturer());
                existingVehicle.setModel(updatedVehicle.getModel());
                existingVehicle.setDailyfare(updatedVehicle.getDailyfare());
                existingVehicle.setFromdate(updatedVehicle.getFromdate());
                existingVehicle.setTodate(updatedVehicle.getTodate());
                existingVehicle.setAvailablelocation(updatedVehicle.getAvailablelocation());
                existingVehicle.setDescription(updatedVehicle.getDescription());

                // Save the updated vehicle to the database
                vehicleService.updateVehicle(existingVehicle);

                return ResponseEntity.ok("Vehicle updated successfully");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }

    @PutMapping("/request-feature/{id}")
    public ResponseEntity<String> requestFeature(@PathVariable Long id) {
        Optional<Vehicles> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicles vehicle = optionalVehicle.get();
            if (vehicle.getFeature() == null) {
                // Only update the feature if it's currently null
                vehicle.setFeature("Requested");
                vehicleRepository.save(vehicle);
                return ResponseEntity.ok("Feature requested successfully");
            } else {
                return ResponseEntity.badRequest().body("Feature is already set");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<String> updateFeatureStatus(
            @PathVariable Long vehicleId,
            @RequestBody Map<String, String> updateRequest
    ) {
        String featureStatus = updateRequest.get("feature");
        if (featureStatus == null || (!featureStatus.equals("Accepted") && !featureStatus.equals("Rejected"))) {
            return ResponseEntity.badRequest().body("Invalid feature status");
        }

        vehicleService.updateFeatureStatus(vehicleId, featureStatus);

        return ResponseEntity.ok("Feature status updated successfully");
    }

    @GetMapping("/recommended/{userId}")
	public ResponseEntity<List<Vehicles>> getRecommendedVehicles(@PathVariable Long userId){
        List<Vehicles> recommendedVehicles = vehicleService.getRecommendedVehicles(userId);
        return ResponseEntity.ok(recommendedVehicles);
    }

}
