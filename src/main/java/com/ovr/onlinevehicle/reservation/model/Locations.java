package com.ovr.onlinevehicle.reservation.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Locations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long location_id;
	private String location;
	private Double latitude;
	private Double longitude;

	public Locations() {

	}

	public Locations(Long location_id, String location, Double latitude, Double longitude) {
		super();
		this.location_id = location_id;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
