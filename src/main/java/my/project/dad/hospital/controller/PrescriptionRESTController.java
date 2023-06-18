package my.project.dad.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.project.dad.hospital.model.Prescription;
import my.project.dad.hospital.repository.PrescriptionRepository;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionRESTController {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	// GET all Prescriptions
	@GetMapping
	public List<Prescription> getPrescriptions() {
		return prescriptionRepository.findAll();
	}
	
	// GET Prescription by ID
	@GetMapping ("{Prescription_id}")
	public Prescription getPrescription(@PathVariable int prescription_id) {
		Prescription prescription = prescriptionRepository.findById(prescription_id).get();
		return prescription;
	}
	
	// POST (insert) new Prescription
	@PostMapping()
	public Prescription insertPrescription(@RequestBody Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	// PUT (update) existing Prescription
	@PutMapping()
	public Prescription updatePrescription(@RequestBody Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	// DELETE Prescription by ID
	@DeleteMapping("{Prescription_id}")
	public ResponseEntity<HttpStatus> deletePrescription(@PathVariable int prescription_id) {
		prescriptionRepository.deleteById(prescription_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
