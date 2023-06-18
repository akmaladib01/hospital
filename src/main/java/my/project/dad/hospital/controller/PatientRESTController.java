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

import my.project.dad.hospital.model.Patient;
import my.project.dad.hospital.repository.PatientRepository;

@RestController
@RequestMapping("/api/patient")
public class PatientRESTController {

	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping
	public List<Patient> getPatients() {
		
		return patientRepository.findAll();
	}
	
	@GetMapping ("{patient_id}")
	public Patient getPatient(@PathVariable Integer patient_id) {
		
		Patient patient = patientRepository.findById(patient_id).get();
		return patient;
	}
	
	@PostMapping()
	public Patient insertPatient(@RequestBody Patient patient) {
		
		return patientRepository.save(patient);
	}
	
	@PutMapping()
	public Patient updatePatient(@RequestBody Patient patient) {
		
		return patientRepository.save(patient);
	}
	
	@DeleteMapping("{patient_id}")
	public ResponseEntity<HttpStatus> deletePatient(@PathVariable Integer patient_id) {
		
		patientRepository.deleteById(patient_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
