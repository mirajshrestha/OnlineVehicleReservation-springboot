package com.ovr.onlinevehicle.reservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ovr.onlinevehicle.reservation.model.Owners;
import java.util.List;


public interface OwnerRepository extends JpaRepository<Owners, Long>{
	Owners findByEmailAndPassword(String email, String password);

	Owners findByEmail(String email);
}
