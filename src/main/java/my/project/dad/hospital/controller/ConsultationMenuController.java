package my.project.dad.hospital.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import my.project.dad.hospital.model.Consultation;
import my.project.dad.hospital.model.Doctor;
import my.project.dad.hospital.model.Patient;

@Controller
public class ConsultationMenuController {

	private String defaultURI = "http://localhost:8080/hospital/api/consultation";

	@GetMapping("/consultation/list")
	public String getConsultation(Model model) {
		
		// The URI for GET consultation
		String uri = "http://localhost:8080/hospital/api/consultation";

		// Get a list of consultations from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Consultation[]> response = restTemplate.getForEntity(uri, Consultation[].class);

		// Parse JSON data to an array of objects
		Consultation[] consultation = response.getBody();

		// Convert the array to a list
		List<Consultation> consultationList = Arrays.asList(consultation);

		// Attach the list to the model as an attribute
		model.addAttribute("consultation",consultationList);

		return "consultation";
	}
	
	/**
	* This method will update or add a consultation.
	*
	* @param consultation
	* @return
	*/

	@RequestMapping("/consultation/save")
	public String updateConsultation (@ModelAttribute Consultation consultation) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create request body
		HttpEntity<Consultation> request = new HttpEntity<Consultation>(consultation);

		String consultationResponse = "";

		if (consultation.getConsultation_id() > 0) {
			
			// This block updates an existing consultation

			// Send request as PUT
			restTemplate.put(defaultURI, request, Consultation.class);
		} 
		
		else {
			
			// This block adds a new consultation

			// Send request as POST
			consultationResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(consultationResponse);

		// Redirect request to display a list of consultations
		return "redirect:/consultation/list";
	}
	
	/**
	 * This method gets a consultation.
	 *
	 * @param consultation_id
	 * @param model
	 * @return
	 */

	@GetMapping("/consultation/{consultation_id}")
	public String getConsultation (@PathVariable Integer consultation_id, Model model) {

		String pageTitle = "New Consultation";
		Consultation consultation = new Consultation();

		// Get a list of patients from the web service
		RestTemplate restTemplatePatient = new RestTemplate();
		ResponseEntity<Patient[]> responsePatient = restTemplatePatient.getForEntity("http://localhost:8080/hospital/api/patient", Patient[].class);

		Patient patientArray[] = responsePatient.getBody();
		List<Patient> patientList = Arrays.asList(patientArray);
		
		// Get a list of doctors from the web service
		RestTemplate restTemplateDoctor = new RestTemplate();
		ResponseEntity<Doctor[]> responseDoctor = restTemplateDoctor.getForEntity("http://localhost:8080/hospital/api/doctor", Doctor[].class);

		Doctor doctorArray[] = responseDoctor.getBody();
		List<Doctor> doctorList = Arrays.asList(doctorArray);
		
		// This block gets a consultation to be updated
		if (consultation_id > 0) {

			// Generate a new URI and append consultation_id to it
			String uri = defaultURI + "/" + consultation_id;

			// Get a consultation from the web service
			RestTemplate restTemplate = new RestTemplate();
			consultation = restTemplate.getForObject(uri, Consultation.class);

			// Give a new title to the page
			pageTitle = "Edit Consultation";
		}

		// Attach values to pass to the front end
		model.addAttribute("patients", patientList);
		model.addAttribute("doctors", doctorList);
		model.addAttribute("consultation", consultation);
		model.addAttribute("pageTitle", pageTitle);

		return "new_consultation";
	}
}
