package com.yash.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.medicine.model.DrugFormTable;
import com.yash.medicine.repo.DrugFormRepository;

@Service
public class DrugFormService {

	@Autowired
	private DrugFormRepository drugFormRepository;

	public DrugFormTable addForm(DrugFormTable drugFormTable) {
		return drugFormRepository.save(drugFormTable);
	}

	public List<DrugFormTable> getAllForm() {
		return drugFormRepository.findAll();
	}

	public DrugFormTable getById(Long id) {
		return drugFormRepository.findById(id).get();

	}

	public void deleteForm(long id) {
		drugFormRepository.deleteById(id);
	}

	public DrugFormTable editForm(DrugFormTable drugFormTable) {
		DrugFormTable exitingForm = drugFormRepository.findById(drugFormTable.getId()).orElse(null);

		if (exitingForm == null) {
			return null;
		}

		exitingForm.setForm(drugFormTable.getForm());

		return drugFormRepository.save(exitingForm);
	}
}
