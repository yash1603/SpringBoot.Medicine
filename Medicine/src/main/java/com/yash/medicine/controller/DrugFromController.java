package com.yash.medicine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.medicine.model.DrugFormTable;
import com.yash.medicine.service.DrugFormService;

import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/medicine")
public class DrugFromController {

	@Autowired
	private DrugFormService drugFormService;

	@PostMapping("/addForm")
	public ResponseEntity<DrugFormTable> addForm(@Valid @RequestBody DrugFormTable drugFormTable) {
		DrugFormTable drugFormTable2 = drugFormService.addForm(drugFormTable);
		return new ResponseEntity<DrugFormTable>(drugFormTable2, HttpStatus.CREATED);
	}

	@GetMapping("/allForm")
	public List<DrugFormTable> getAllForm() {
		return drugFormService.getAllForm();
	}

	@GetMapping("/get/id")
	public DrugFormTable getById(@RequestParam Long id) {
		return drugFormService.getById(id);
	}

	@PutMapping("/editForm")
	public DrugFormTable editForm(@RequestBody DrugFormTable drugFormTable) {
		return drugFormService.editForm(drugFormTable);
	}

	@DeleteMapping("/deletForm")
	public String deleteForm(@RequestParam Long id) {
		drugFormService.deleteForm(id);
		return "deleted sucesfully";
	}
}
