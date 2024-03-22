package com.ovr.onlinevehicle.reservation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ovr.onlinevehicle.reservation.model.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long>{

	


}
