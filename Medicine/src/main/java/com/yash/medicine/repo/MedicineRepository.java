package com.yash.medicine.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.medicine.model.Medicine;



@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

//		List<Medicine> findByisActive(Boolean isActive);

	List<Medicine> findByisActiveTrue();

	List<Medicine> findByisActiveFalse();

}
