package com.yash.medicine.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.medicine.model.Medicine;
import com.yash.medicine.model.Packaging;
import com.yash.medicine.repo.MedicineRepository;


@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private DrugFormService drugFormService;

	public Medicine addMedicine(Medicine medicine) {
		medicine.setIsActive(medicine.getQuantity()>0);
		return medicineRepository.save(medicine);
	}

	public Medicine getById(String id) {
		return medicineRepository.findById(id).get();
	}

	public void deleteMedicine(String id) {

		medicineRepository.deleteById(id);
	}

	public Medicine editMedicine(Medicine medicine) {

		
		Medicine exitingMedicine = medicineRepository.findById(medicine.getId()).orElse(null);

		if (exitingMedicine == null) {
			return null;
		}
		Long id = medicine.getForm().getId();

		exitingMedicine.setDrugBrandName(medicine.getDrugBrandName());
		exitingMedicine.setDrugGenericName(medicine.getDrugGenericName());
		exitingMedicine.setForm(drugFormService.getById(id));
		exitingMedicine.setManufacturerCompany(medicine.getManufacturerCompany());
		exitingMedicine.setMfgDate(medicine.getMfgDate());
		exitingMedicine.setExpiryDate(medicine.getExpiryDate());
		exitingMedicine.setMrp(medicine.getMrp());
		exitingMedicine.setOtherDetails(medicine.getOtherDetails());
		exitingMedicine.setPackaging(medicine.getPackaging());
		exitingMedicine.setQuantity(medicine.getQuantity());
		exitingMedicine.setIsActive(medicine.getQuantity()>0);

		return medicineRepository.save(exitingMedicine);

	}

	public List<Medicine> searchForMedicine(String value) {

		if (value.contains("true")) {
			return medicineRepository.findByisActiveTrue();
		} else if (value.contains("false")) {
			return medicineRepository.findByisActiveFalse();
		} else {
			return medicineRepository.findAll();
		}
	}
	public List<Packaging> getAllPackege() {
		return Arrays.asList(Packaging.values());
	}
}
