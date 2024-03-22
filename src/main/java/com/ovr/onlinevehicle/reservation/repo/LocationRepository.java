package com.ovr.onlinevehicle.reservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ovr.onlinevehicle.reservation.model.Locations;

public interface LocationRepository extends JpaRepository<Locations, Long>{

}
