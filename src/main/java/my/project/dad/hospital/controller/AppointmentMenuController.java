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
	
	/**
	* This method will update or add an order type.
	*
	* @param orderType
	* @return
	*/

	@RequestMapping("/appointment/save")
	public String updateAppointment (@ModelAttribute Appointment appointment) {

		// Create a new RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Create request body
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(appointment);

		String appointmentResponse = "";

		if (appointment.getAppointment_id() > 0) {
			
			// This block update an new order type and

			// Send request as PUT
			restTemplate.put(defaultURI, request, Appointment.class);
		} 
		
		else {
			
			// This block add a new order type

			// send request as POST
			appointmentResponse = restTemplate.postForObject(defaultURI, request, String.class);
		}

		System.out.println(appointmentResponse);

		// Redirect request to display a list of order type
		return "redirect:/appointment/list";
	}
	
	/**
	 *This method gets an order type
	 *
	 *@param orderTypeld
	 *@param model
	 *@return
	 */

	@GetMapping("/appointment/{appointment_id}")
	public String getAppointment (@PathVariable Integer appointment_id, Model model) {

		String pageTitle = "New appointment";
		Appointment appointment = new Appointment();

		// This block get an order type to be updated
		if (appointment_id > 0) {

			// Generate new URI and append orderTypeld to it
			String uri = defaultURI + "/" + appointment_id;

			// Get an order type from the web service
			RestTemplate restTemplate = new RestTemplate();
			appointment = restTemplate.getForObject(uri, Appointment.class);

			// Give a new title to the page
			pageTitle = "Edit Appointment";
		}

		// Attach value to pass to front end
		model.addAttribute("appointment", appointment);
		model.addAttribute("pageTitle",pageTitle);

		return "new_appointment";
	}
	
	/**
	*This method deletes an order type
	*
	* @param orderTypeld
	* @return
	*/
	@RequestMapping("/appointment/delete/{appointment_id}")
	public String deleteAppointment(@PathVariable Integer appointment_id) {
		
		// Generate new URI, similar to the mapping in OrderTypeRESTController
		String uri = defaultURI + "/{appointment_id}";

		// Send a DELETE request and attach the value of orderTypeId into URI
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri,Map.of("appointment_id", Integer.toString(appointment_id)));

		return "redirect:/appointment/list";

	}
}