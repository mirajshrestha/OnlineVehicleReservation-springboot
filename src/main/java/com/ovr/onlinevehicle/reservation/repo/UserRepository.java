package com.ovr.onlinevehicle.reservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ovr.onlinevehicle.reservation.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
//	@Query("select u from User u where u.email = : email")
//	public User getUserByUserName(@Param("email") String email);
	User findOneByEmailAndPassword(String email, String password);

	User findByEmail(String email);
}
