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
	
	@RequestMapping("/prescription/save")
	public String updatePrescription (@ModelAttribute Prescription prescription) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		// Create request body
		HttpEntity<Prescription> request = new HttpEntity<Prescription>(prescription);

		String prescriptionResponse = "";

		if (prescription.getPrescription_id() > 0) {
			
			// This block updates an existing prescription

			// Send request as PUT
			restTemplate.put(defaultURI, request, Prescription.class);
		} else {
			
			// This block adds a new prescription

			// Send request as POST
			prescriptionResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(prescriptionResponse);

		// Redirect request to display a list of prescriptions
		return "redirect:/prescription/list";
	}
	
	/**
	 * This method gets a prescription
	 *
	 * @param prescription_id
	 * @param model
	 * @return
	 */

	@GetMapping("/prescription/{prescription_id}")
	public String getPrescription (@PathVariable Integer prescription_id, Model model) {
		
		String pageTitle = "New prescription";
		Prescription prescription = new Prescription();
		
		// Retrieve a list of consultations from the web service
		RestTemplate restTemplateConsultation = new RestTemplate();
		ResponseEntity<Consultation[]> responseConsultation = restTemplateConsultation.getForEntity("http://localhost:8080/hospital/api/consultation", Consultation[].class);

		Consultation consultationArray[] = responseConsultation.getBody();
		List<Consultation> consultationList = Arrays.asList(consultationArray);
		
		// This block gets a prescription to be updated
		if (prescription_id > 0) {

			// Generate new URI and append prescription_id to it
			String uri = defaultURI + "/" + prescription_id;
			
			// Get a prescription from the web service
			RestTemplate restTemplate = new RestTemplate();
			prescription = restTemplate.getForObject(uri, Prescription.class);

			// Give a new title to the page
			//pageTitle = "Edit Appointment";
		}

		// Attach values to pass to the front end
		model.addAttribute("consultations", consultationList);
		model.addAttribute("prescription", prescription);
		model.addAttribute("pageTitle", pageTitle);

		return "new_prescription";
	}
}
