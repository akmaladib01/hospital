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

import my.project.dad.hospital.model.Doctor;
import my.project.dad.hospital.repository.DoctorRepository;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRESTController {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping
	public List<Doctor> getDoctors() {
		// Retrieve all doctors from the repository
		return doctorRepository.findAll();
	}
	
	@GetMapping ("{doctor_id}")
	public Doctor getDoctor(@PathVariable int doctor_id) {
		// Retrieve a specific doctor by ID from the repository
		Doctor doctor = doctorRepository.findById(doctor_id).get();
		return doctor;
	}
	
	@PostMapping()
	public Doctor insertDoctor(@RequestBody Doctor doctor) {
		// Insert a new doctor into the repository
		return doctorRepository.save(doctor);
	}
	
	@PutMapping()
	public Doctor updateDoctor(@RequestBody Doctor doctor) {
		// Update an existing doctor in the repository
		return doctorRepository.save(doctor);
	}
	
	@DeleteMapping("{doctor_id}")
	public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable int doctor_id) {
		// Delete a doctor from the repository by ID
		doctorRepository.deleteById(doctor_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
