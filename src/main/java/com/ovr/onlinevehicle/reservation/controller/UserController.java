package com.ovr.onlinevehicle.reservation.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.hibernate.boot.archive.spi.InputStreamAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.ovr.onlinevehicle.reservation.config.LoginRequest;
import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.UserRepository;
import com.ovr.onlinevehicle.reservation.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllTopics() {
		return userService.getAllTopics();
	}

	@PostMapping("/register")
	public ResponseEntity<String> processRegistration(@RequestPart("image") MultipartFile image, @RequestPart("user") User user) throws IOException {
		
		String imagePath = userService.saveImage(image);
		user.setLicense_name(imagePath);
		
		userService.registerUser(user);
		return ResponseEntity.ok("User registration Successful");
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		User user = userService.authenticate(email, password);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
		session.setAttribute("userId", user.getUser_id());
		session.setAttribute("email", user.getEmail());

		return ResponseEntity.ok("Login successful");
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("Logout Successful");
	}

	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("userId") != null) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.getUserById(userId);

			if (user != null) {
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(
            @RequestBody User updatedUser,
            HttpSession session
    ) {
        // Check if the user is authenticated
        if (session != null && session.getAttribute("userId") != null) {
            Long userId = (Long) session.getAttribute("userId");

            // Fetch the existing user from the database
            User existingUser = userService.getUserById(userId);

            // Check if the user exists
            if (existingUser != null) {
                // Update the user details
                existingUser.setName(updatedUser.getName());
                existingUser.setAddress(updatedUser.getAddress());
                existingUser.setContact(updatedUser.getContact());

                // Save the updated user to the database
                userService.registerUser(existingUser);

                return ResponseEntity.ok("User updated successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }

	
}
