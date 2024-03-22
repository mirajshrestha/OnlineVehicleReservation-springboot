package com.ovr.onlinevehicle.reservation.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owners{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long owners_id;
	private String fullname;
	private String email;
	private String password;
	private String contact;
	private boolean enabled;
	private String role;
	
	@OneToMany(mappedBy = "owners")
	@JsonIgnore
	private Set<Vehicles> vehicle = new HashSet<>();
	
	@OneToMany(mappedBy = "owners", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Bookings> bookings;
	
	public Owners() {
		
	}
	
	
	


	public Owners(Long owners_id, String fullname, String email, String password, String contact, boolean enabled,
			String role, Set<Vehicles> vehicle, List<Bookings> bookings) {
		super();
		this.owners_id = owners_id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.enabled = enabled;
		this.role = role;
		this.vehicle = vehicle;
		this.bookings = bookings;
	}





	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Long getOwners_id() {
		return owners_id;
	}

	public void setOwners_id(Long owners_id) {
		this.owners_id = owners_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set<Vehicles> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<Vehicles> vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Bookings> getBookings() {
		return bookings;
	}


	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

		
}
