package com.ovr.onlinevehicle.reservation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovr.onlinevehicle.reservation.model.Admin;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.repo.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Admin authenticate(String username, String password) {
		// You should implement your logic to check if the provided credentials are
		// valid
		// For example, you might query your database for an admin with the given
		// username and password
		return adminRepository.findByUsernameAndPassword(username, password);
	}

	public Admin getUserById(Long adminId) {
		return adminRepository.findById(adminId).orElse(null);
	}
}
