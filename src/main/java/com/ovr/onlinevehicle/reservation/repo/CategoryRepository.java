package com.ovr.onlinevehicle.reservation.repo;

import com.ovr.onlinevehicle.reservation.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
