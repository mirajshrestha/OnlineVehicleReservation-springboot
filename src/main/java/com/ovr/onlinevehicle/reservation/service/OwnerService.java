package com.ovr.onlinevehicle.reservation.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovr.onlinevehicle.reservation.model.Bookings;
import com.ovr.onlinevehicle.reservation.model.Owners;
import com.ovr.onlinevehicle.reservation.model.User;
import com.ovr.onlinevehicle.reservation.repo.OwnerRepository;



@Service
public class OwnerService {
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	
//	private List<Owners> owners = new ArrayList<>(Arrays.asList(
//			new Owners(1L, "Miraj Shrestha", "mirajshrestha10@gmail.com", "123456", "9845076998", null)
//			));
//	
	public void registerOwner(Owners owners) {
		owners.setRole("ROLE_OWNER");
		owners.setEnabled(false);
		ownerRepository.save(owners);
		
	}

	public List<Owners> getAllTopics() {
		List<Owners> owners = new ArrayList<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}
	
	public Owners loginOwner(String email, String password) {
		Owners owner = ownerRepository.findByEmailAndPassword(email, password);
		return owner;
	}

	public Owners authenticate(String email, String password) {
		Owners owners = ownerRepository.findByEmail(email);
		if(owners != null && owners.getPassword().equals(password)) {
			return owners;
		}
		return null;
	}

	public Owners getUserById(Long ownerId) {
		return ownerRepository.findById(ownerId).orElse(null);
	}

	public void updateOwner(Owners owner) {
        ownerRepository.save(owner);
    }

	public List<Owners> getAllOwners() {
		List<Owners> owner = new ArrayList<>();
		ownerRepository.findAll().forEach(owner::add);
		return owner;
	}
	
}
