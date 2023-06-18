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
import org.springframework.web.client.RestTemplate;

import my.project.dad.hospital.model.Doctor;

@Controller
public class DoctorMenuController {

	private String defaultURI = "http://localhost:8080/hospital/api/doctor";

	@GetMapping("/doctor/list")
	public String getDoctor(Model model) {
		
		// The URI for GET order types
		String uri = "http://localhost:8080/hospital/api/doctor";

		// Get a list order types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Doctor[]> response = restTemplate.getForEntity(uri, Doctor[].class);

		// Parse JSON data to array of object
		Doctor doctor[] = response.getBody();

		// Parse an array to a list object
		List<Doctor> doctorList = Arrays.asList(doctor);

		// Attach list to model as attribute
		model.addAttribute("doctor",doctorList);

		return "doctor";
	}
	
	/**
	* This method will update or add an order type.
	*
	* @param orderType
	* @return
	*/

	@RequestMapping("/doctor/save")
	public String updateDoctor (@ModelAttribute Doctor doctor) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create request body
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(doctor);

		String DoctorResponse = "";

		if (doctor.getDoctor_id() > 0) {
			
			// This block update an new order type and

			// Send request as PUT
			restTemplate.put(defaultURI, request, Doctor.class);
		} 
		
		else {
			
			// This block add a new order type

			// send request as POST
			DoctorResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(DoctorResponse);

		// Redirect request to display a list of order type
		return "redirect:/doctor/list";
	}
}
