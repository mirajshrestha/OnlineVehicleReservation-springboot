package com.ovr.onlinevehicle.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovr.onlinevehicle.reservation.config.AdminLoginRequest;
import com.ovr.onlinevehicle.reservation.model.Admin;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login") // Corrected the mapping annotation
	public ResponseEntity<?> login(@RequestBody AdminLoginRequest loginRequest, HttpSession session){
	    String username = loginRequest.getUsername();
	    String password = loginRequest.getPassword();

	    // Your admin authentication logic here...
	    Admin admin = adminService.authenticate(username, password);
	    if (admin == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

	    session.setAttribute("adminId", admin.getAdmin_id());

		return ResponseEntity.ok("Login successful");
	}

	
	@GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to logout
        return ResponseEntity.ok("Logout successful");
    }
	
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("adminId") != null) {
			Long adminId = (Long) session.getAttribute("adminId");
			Admin admin = adminService.getUserById(adminId);

			if (admin != null) {
				return ResponseEntity.ok(admin);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
