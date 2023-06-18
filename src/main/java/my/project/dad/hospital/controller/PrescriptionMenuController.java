package my.project.dad.hospital.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import my.project.dad.hospital.model.Prescription;

@Controller
public class PrescriptionMenuController {

	private String defaultURI = "http://localhost:8080/hospital/api/prescription";

	@GetMapping("/prescription/list")
	public String getPrescription(Model model) {

		// The URI for GET Prescription types
		String uri = "http://localhost:8080/hospital/api/prescription";

		// Get a list of Prescription types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Prescription[]> response = restTemplate.getForEntity(uri, Prescription[].class);

		// Parse JSON data to an array of objects
		Prescription[] prescriptions = response.getBody();

		// Convert the array to a list
		List<Prescription> prescriptionList = Arrays.asList(prescriptions);

		// Attach the list to the model as an attribute
		model.addAttribute("prescription", prescriptionList);

		return "prescription";
	}
}
