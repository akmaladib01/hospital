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
		// The URI for GET doctors
		String uri = "http://localhost:8080/hospital/api/doctor";

		// Get a list of doctors from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Doctor[]> response = restTemplate.getForEntity(uri, Doctor[].class);

		// Parse JSON data into an array of objects
		Doctor[] doctorArray = response.getBody();

		// Convert the array into a list
		List<Doctor> doctorList = Arrays.asList(doctorArray);

		// Attach the doctor list to the model as an attribute
		model.addAttribute("doctor", doctorList);

		return "doctor";
	}

	/**
	 * This method will update or add a doctor.
	 *
	 * @param doctor
	 * @return
	 */
	@RequestMapping("/doctor/save")
	public String updateDoctor(@ModelAttribute Doctor doctor) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create a request body
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(doctor);

		String doctorResponse = "";

		if (doctor.getDoctor_id() > 0) {
			// This block updates an existing doctor

			// Send the request as a PUT
			restTemplate.put(defaultURI, request, Doctor.class);
		} else {
			// This block adds a new doctor

			// Send the request as a POST
			doctorResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(doctorResponse);

		// Redirect the request to display a list of doctors
		return "redirect:/doctor/list";
	}
}
