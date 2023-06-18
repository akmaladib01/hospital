package my.project.dad.hospital.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import my.project.dad.hospital.model.Patient;

@Controller
public class PatientMenuController {
	
	private String defaultURI = "http://localhost:8080/hospital/api/patient";

	@GetMapping("/patient/list")
	public String getPatient(Model model) {
		
		// The URI for GET patient types
		String uri = "http://localhost:8080/hospital/api/patient";

		// Get a list of patient types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient[]> response = restTemplate.getForEntity(uri, Patient[].class);

		// Parse JSON data to an array of objects
		Patient[] patients = response.getBody();

		// Convert the array to a list
		List<Patient> patientList = Arrays.asList(patients);

		// Attach the list to the model as an attribute
		model.addAttribute("patient", patientList);

		return "patient";
	}
	
//	@GetMapping("/patient")
//	public String appointmentPage() {
//		return "patient.html";
//	}
//	
//	/**
//	* This method will update or add a patient.
//	*
//	* @param patient
//	* @return
//	*/
//
//	@RequestMapping("/patient/save")
//	public String updatePatient (@ModelAttribute Patient patient) {
//
//		// Create a new RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//
//		// Create request body
//		HttpEntity<Patient> request = new HttpEntity<Patient>(patient);
//
//		String patientResponse = "";
//
//		if (patient.getPatient_id() > 0) {
//			// This block updates an existing patient
//
//			// Send request as PUT
//			restTemplate.put(defaultURI, request, Patient.class);
//		} else {
//			// This block adds a new patient
//
//			// Send request as POST
//			patientResponse = restTemplate.postForObject(defaultURI, request, String.class);
//		}
//
//		System.out.println(patientResponse);
//
//		// Redirect request to display a list of patients
//		return "redirect:/patient/list";
//	}
}
