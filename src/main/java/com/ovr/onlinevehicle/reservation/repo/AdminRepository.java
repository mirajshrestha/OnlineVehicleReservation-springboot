package com.ovr.onlinevehicle.reservation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ovr.onlinevehicle.reservation.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByUsernameAndPassword(String username, String password);
}
