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

import my.project.dad.hospital.model.Appointment;

@Controller
public class AppointmentMenuController {

	private String defaultURI = "http://localhost:8080/hospital/api/appointment";

	@GetMapping("/appointment/list")
	public String getAppointment(Model model) {

		// The URI for GET appointment types
		String uri = "http://localhost:8080/hospital/api/appointment";

		// Get a list of appointment types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Appointment[]> response = restTemplate.getForEntity(uri, Appointment[].class);

		// Parse JSON data to an array of objects
		Appointment[] appointments = response.getBody();

		// Convert the array to a list
		List<Appointment> appointmentList = Arrays.asList(appointments);

		// Attach the list to the model as an attribute
		model.addAttribute("appointment", appointmentList);

		return "appointment";
	}
}