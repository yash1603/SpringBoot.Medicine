package com.yash.medicine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.medicine.model.DrugFormTable;

@Repository
public interface DrugFormRepository extends JpaRepository<DrugFormTable, Long> {

}
