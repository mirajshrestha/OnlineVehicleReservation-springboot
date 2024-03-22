package com.ovr.onlinevehicle.reservation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.model.Vehicles;
import com.ovr.onlinevehicle.reservation.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//	private List<User> user = new ArrayList<>(Arrays.asList(
//			new User(1L, "Miraj Shrestha", "mirajshrestha10@gmail.com", "123456", "9845076998", null, null)
//			));
	
	public void registerUser(User user) {
//		this.passwordEncoder.encode(user.getPassword());
		user.setRole("ROLE_USER");
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	public List<User> getAllTopics() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User authenticate(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user!=null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public String saveImage(MultipartFile image) throws IOException {
		
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
	

	
}
