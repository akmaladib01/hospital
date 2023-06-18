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

import my.project.dad.hospital.model.Consultation;
import my.project.dad.hospital.repository.ConsultationRepository;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationRESTController {

	@Autowired
	private ConsultationRepository consultationRepository;
	
	@GetMapping
	public List<Consultation> getConsultations() {
		
		return consultationRepository.findAll();
	}
	
	@GetMapping ("{consultation_id}")
	public Consultation getConsultation(@PathVariable int consultation_id) {
		
		Consultation consultation = consultationRepository.findById(consultation_id).get();
		return consultation;
	}
	
	@PostMapping()
	public Consultation insertConsultation(@RequestBody Consultation consultation) {
		
		return consultationRepository.save(consultation);
	}
	
	@PutMapping()
	public Consultation updateConsultation(@RequestBody Consultation consultation) {
		
		return consultationRepository.save(consultation);
	}
	
	@DeleteMapping("{Consultation_id}")
	public ResponseEntity<HttpStatus> deleteConsultation(@PathVariable int consultation_id) {
		
		consultationRepository.deleteById(consultation_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
