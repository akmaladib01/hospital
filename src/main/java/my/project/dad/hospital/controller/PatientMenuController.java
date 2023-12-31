package my.project.dad.hospital.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * This method will update or add a patient.
	 *
	 * @param patient
	 * @return
	 */
	@RequestMapping("/patient/save")
	public String updatePatient(@ModelAttribute Patient patient) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create request body
		HttpEntity<Patient> request = new HttpEntity<Patient>(patient);

		String patientResponse = "";

		if (patient.getPatient_id() > 0) {
			// This block updates an existing patient

			// Send request as PUT
			restTemplate.put(defaultURI, request, Patient.class);
		} else {
			// This block adds a new patient

			// Send request as POST
			patientResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(patientResponse);

		// Redirect request to display a list of patients
		return "redirect:/patient/list";
	}
	
	/**
	 * This method gets a patient.
	 *
	 * @param patient_id
	 * @param model
	 * @return
	 */
	@GetMapping("/patient/{patient_id}")
	public String getPatient(@PathVariable Integer patient_id, Model model) {

		String pageTitle = "New Patient";
		Patient patient = new Patient();

		// This block gets a patient to be updated
		if (patient_id > 0) {

			// Generate new URI and append patient_id to it
			String uri = defaultURI + "/" + patient_id;

			// Get a patient from the web service
			RestTemplate restTemplate = new RestTemplate();
			patient = restTemplate.getForObject(uri, Patient.class);

			// Give a new title to the page
			pageTitle = "Edit Patient";
		}

		// Attach values to pass to the front end
		model.addAttribute("patient", patient);
		model.addAttribute("pageTitle", pageTitle);

		return "new_patient";
	}
	
	/**
	 * This method deletes a patient.
	 *
	 * @param patient_id
	 * @return
	 */
	@RequestMapping("/patient/delete/{patient_id}")
	public String deletePatient(@PathVariable Integer patient_id) {
		
		// Generate new URI, similar to the mapping in OrderTypeRESTController
		String uri = defaultURI + "/{patient_id}";

		// Send a DELETE request and attach the value of patient_id into URI
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri, Map.of("patient_id", Integer.toString(patient_id)));

		return "redirect:/patient/list";
	}
}
