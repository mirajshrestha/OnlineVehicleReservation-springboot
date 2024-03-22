package com.ovr.onlinevehicle.reservation.repo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ovr.onlinevehicle.reservation.model.Vehicles;

public interface VehicleRepository extends JpaRepository<Vehicles, Long>{

	@Query("SELECT v FROM Vehicles v WHERE v.owners.owners_id = :ownerId")
	List<Vehicles> findByOwners_id(@Param("ownerId")Long ownerid);
	
	@Query("SELECT v FROM Vehicles v WHERE v.availablelocation = :location AND v.fromdate <= :pickUpDate AND v.todate >= :returnDate")
	List<Vehicles> findAvailableVehicles(@Param("location") String location, @Param("pickUpDate") Date pickUpDate, @Param("returnDate") Date returnDate);
	
	@Query(value = "SELECT * FROM vehicles LEFT JOIN locations ON vehicles.availablelocation = locations.location", nativeQuery = true)
	List<Object[]> findVehiclesWithLocations();

	

	
}
