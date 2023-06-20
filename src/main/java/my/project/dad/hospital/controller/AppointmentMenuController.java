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
import my.project.dad.hospital.model.Patient;
import my.project.dad.hospital.model.Doctor;
import my.project.dad.hospital.model.Room;

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
	
	/**
	* This method will update or add an order type.
	*
	* @param orderType The appointment object to be updated or added
	* @return A string representing the view to redirect to
	*/

	@RequestMapping("/appointment/save")
	public String updateAppointment (@ModelAttribute Appointment appointment) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create request body
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(appointment);

		String appointmentResponse = "";

		if (appointment.getAppointment_id() > 0) {
			
			// This block updates an existing appointment

			// Send request as PUT
			restTemplate.put(defaultURI, request, Appointment.class);
		} 
		
		else {
			
			// This block adds a new appointment

			// Send request as POST
			appointmentResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(appointmentResponse);

		// Redirect request to display a list of appointment types
		return "redirect:/appointment/list";
	}
	
	/**
	 * This method retrieves an appointment.
	 *
	 * @param appointment_id The ID of the appointment to retrieve
	 * @param model The model to pass data to the view
	 * @return A string representing the view to render
	 */

	@GetMapping("/appointment/{appointment_id}")
	public String getAppointment (@PathVariable Integer appointment_id, Model model) {
		
		String pageTitle = "New appointment";
		Appointment appointment = new Appointment();
		
		// Retrieve the list of patients from the web service
		RestTemplate restTemplatePatient = new RestTemplate();
		ResponseEntity<Patient[]> responsePatient = restTemplatePatient.getForEntity("http://localhost:8080/hospital/api/patient", Patient[].class);

		Patient patientArray[] = responsePatient.getBody();
		List<Patient> patientList = Arrays.asList(patientArray);
		
		// Retrieve the list of doctors from the web service
		RestTemplate restTemplateDoctor = new RestTemplate();
		ResponseEntity<Doctor[]> responseDoctor = restTemplateDoctor.getForEntity("http://localhost:8080/hospital/api/doctor", Doctor[].class);

		Doctor doctorArray[] = responseDoctor.getBody();
		List<Doctor> doctorList = Arrays.asList(doctorArray);
		
		// Retrieve the list of rooms from the web service
		RestTemplate restTemplateRoom = new RestTemplate();
		ResponseEntity<Room[]> responseRoom = restTemplateRoom.getForEntity("http://localhost:8080/hospital/api/room", Room[].class);

		Room roomArray[] = responseRoom.getBody();
		List<Room> roomList = Arrays.asList(roomArray);
		
		// This block retrieves an appointment to be updated
		if (appointment_id > 0) {

			// Generate new URI and append appointment_id to it
			String uri = defaultURI + "/" + appointment_id;
			
			// Get an appointment from the web service
			RestTemplate restTemplate = new RestTemplate();
			appointment = restTemplate.getForObject(uri, Appointment.class);

			// Give a new title to the page
			pageTitle = "Edit Appointment";
		}

		// Attach values to be passed to the front end
		model.addAttribute("patients", patientList);
		model.addAttribute("doctors", doctorList);
		model.addAttribute("rooms", roomList);
		model.addAttribute("appointment", appointment);
		model.addAttribute("pageTitle", pageTitle);

		return "new_appointment";
	}
}
