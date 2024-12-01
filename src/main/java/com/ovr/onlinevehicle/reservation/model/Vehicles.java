package com.ovr.onlinevehicle.reservation.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Vehicles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vehicle_id;
	private String manufacturer;
	private String model;
	private Integer dailyfare;
	private Date fromdate;
	private Date todate;
	private String availablelocation;
	private String category;
	private String feature;
	private String description;
	private String carimg;

	@ManyToOne
	@JoinColumn(name = "owners_id")
	private Owners owners;

	@OneToMany(mappedBy = "vehicles", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Bookings> bookings;

	public Vehicles() {

	}

	public Vehicles(Long vehicle_id, String manufacturer, String model, Integer dailyfare, Date fromdate, Date todate,
			String availablelocation, String category,String feature, String description, String carimg,
			Owners owners, List<Bookings> bookings) {
		super();
		this.vehicle_id = vehicle_id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dailyfare = dailyfare;
		this.fromdate = fromdate;
		this.todate = todate;
		this.availablelocation = availablelocation;
		this.category = category;
		this.feature = feature;
		this.description = description;
		this.carimg = carimg;
		this.owners = owners;
		this.bookings = bookings;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getDailyfare() {
		return dailyfare;
	}

	public void setDailyfare(Integer dailyfare) {
		this.dailyfare = dailyfare;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getAvailablelocation() {
		return availablelocation;
	}

	public void setAvailablelocation(String availablelocation) {
		this.availablelocation = availablelocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCarimg() {
		return carimg;
	}

	public void setCarimg(String carimg) {
		this.carimg = carimg;
	}

	public Owners getOwners() {
		return owners;
	}

	public void setOwners(Owners owners) {
		this.owners = owners;
	}

	public List<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
