package com.yash.medicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.medicine.model.Medicine;
import com.yash.medicine.model.Packaging;
import com.yash.medicine.service.MedicineService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@PostMapping("/addMedicine")
	public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
		medicineService.addMedicine(medicine);
		return new ResponseEntity<Medicine>(medicine, HttpStatus.CREATED);
	}

	@GetMapping("/filter/{value}")
	public List<Medicine> getFilterForMedicine(@PathVariable String value) {

		return medicineService.searchForMedicine(value);

	}

	@GetMapping("/getByid")
	public Medicine getById(@RequestParam String id) {
		return medicineService.getById(id);
	}

	@DeleteMapping("/deleteMedicine/{id}")
	public String deleteMedicine(@PathVariable String id) {
		medicineService.deleteMedicine(id);
		return "deleted";
	}

	@PutMapping("/editMedicine")
	public ResponseEntity<Medicine> editMedicine(@Valid @RequestBody Medicine medicine) {
		Medicine medicine2= medicineService.editMedicine(medicine);
		return new ResponseEntity<Medicine>(medicine2, HttpStatus.CREATED);
	}
	@GetMapping("/getPackege")
	public List<Packaging> getPackege(){
		return medicineService.getAllPackege();
	}
}
