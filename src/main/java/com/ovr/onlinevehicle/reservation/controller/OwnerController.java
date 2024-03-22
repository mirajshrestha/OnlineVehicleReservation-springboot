package com.ovr.onlinevehicle.reservation.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ovr.onlinevehicle.reservation.config.LoginRequest;
import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.OwnerRepository;
import com.ovr.onlinevehicle.reservation.service.OwnerService;
import com.ovr.onlinevehicle.reservation.service.VehicleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
	

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private OwnerRepository ownerRepository;
	

	@GetMapping("/hello")
    public String hello() {
        return "Full Stack Java with Spring Boot & VueJS!";
    }
	
	@GetMapping("/owners")
	public List<Owners> getAllTopics() {
		return ownerService.getAllTopics();
	}
	
	@PostMapping("/register")
	public void processRegistration(@RequestBody Owners owners) {
		ownerService.registerOwner(owners);
		
	}	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginOwner(@RequestBody LoginRequest loginRequest, HttpSession session){
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Owners owners = ownerService.authenticate(email,password);
		if(owners == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
			
		}
		session.setAttribute("ownerId", owners.getOwners_id());
		session.setAttribute("email", owners.getEmail());
		
		return ResponseEntity.ok("Login Successful");
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok("Logout Successful");
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("ownerId") != null) {
			Long ownerId = (Long) session.getAttribute("ownerId");
			Owners owners = ownerService.getUserById(ownerId);
			
			if(owners != null) {
				return ResponseEntity.ok(owners);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateOwner(
            @RequestBody Owners updatedOwner,
            HttpSession session
    ) {
        // Check if the user is authenticated
		if (session != null && session.getAttribute("ownerId") != null) {
	        Long ownerId = (Long) session.getAttribute("ownerId");

	        // Fetch the existing owner from the database
	        Owners existingOwner = ownerService.getUserById(ownerId);

	        // Check if the owner exists
	        if (existingOwner != null) {
	            // Update the owner details
	            existingOwner.setFullname(updatedOwner.getFullname());
	            existingOwner.setContact(updatedOwner.getContact());

	            // Save the updated owner to the database
	            ownerService.updateOwner(existingOwner);

	            return ResponseEntity.ok("Owner updated successfully");
	        }
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }
	
	@GetMapping("/all")
	public List<Owners> getAllOwners() {
		return ownerService.getAllOwners();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
	    Optional<Owners> optionalOwner = ownerRepository.findById(id);
	    if (optionalOwner.isPresent()) {
	        ownerRepository.deleteById(id);
	        return ResponseEntity.ok("Owner deleted successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
}
