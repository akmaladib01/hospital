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

import my.project.dad.hospital.model.Appointment;
import my.project.dad.hospital.repository.AppointmentRepository;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentRESTController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	// GET all appointments
	@GetMapping
	public List<Appointment> getAppointments() {
		return appointmentRepository.findAll();
	}
	
	// GET appointment by ID
	@GetMapping("{appointment_id}")
	public Appointment getAppointment(@PathVariable long appointment_id) {
		Appointment appointment = appointmentRepository.findById(appointment_id).get();
		return appointment;
	}
	
	// POST (insert) new appointment
	@PostMapping()
	public Appointment insertAppointment(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	// PUT (update) existing appointment
	@PutMapping()
	public Appointment updateAppointment(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	// DELETE appointment by ID
	@DeleteMapping("{appointment_id}")
	public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable long appointment_id) {
		appointmentRepository.deleteById(appointment_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
