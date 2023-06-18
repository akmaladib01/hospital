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

@Controller
public class ConsultationMenuController {

	private String defaultURI = "http://localhost:8080/hospital/api/consultation";

	@GetMapping("/consultation/list")
	public String getConsultation(Model model) {
		
		// The URI for GET order types
		String uri = "http://localhost:8080/hospital/api/consultation";

		// Get a list order types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Consultation[]> response = restTemplate.getForEntity(uri, Consultation[].class);

		// Parse JSON data to array of object
		Consultation consultation[] = response.getBody();

		// Parse an array to a list object
		List<Consultation> consultationList = Arrays.asList(consultation);

		// Attach list to model as attribute
		model.addAttribute("consultation",consultationList);

		return "consultation";
	}
	
//	/**
//	* This method will update or add an order type.
//	*
//	* @param orderType
//	* @return
//	*/
//
//	@RequestMapping("/consultation/save")
//	public String updateConsultation (@ModelAttribute Consultation consultation) {
//
//		// Create a new RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//
//		// Create request body
//		HttpEntity<Consultation> request = new HttpEntity<Consultation>(consultation);
//
//		String ConsultationResponse = "";
//
//		if (consultation.getConsultation_id() == "A") {
//			
//			// This block update an new order type and
//
//			// Send request as PUT
//			restTemplate.put(defaultURI, request, Consultation.class);
//		} 
//		
//		else {
//			
//			// This block add a new order type
//
//			// send request as POST
//			ConsultationResponse = restTemplate.postForObject(defaultURI, request, String.class);
//		}
//
//		System.out.println(ConsultationResponse);
//
//		// Redirect request to display a list of order type
//		return "redirect:/consultation/list";
//	}
//	
//	/**
//	 *This method gets an order type
//	 *
//	 *@param orderTypeld
//	 *@param model
//	 *@return
//	 */
//
//	@GetMapping("/consultation/{consultationId}")
//	public String getConsultation (@PathVariable String consultationId, Model model) {
//
//		String pageTitle = "New Consultation";
//		Consultation consultation = new Consultation();
//
//		// This block get an order type to be updated
//		if (consultationId == "A") {
//
//			// Generate new URI and append Consultationld to it
//			String uri = defaultURI + "/" + consultationId;
//
//			// Get an order type from the web service
//			RestTemplate restTemplate = new RestTemplate();
//			consultation = restTemplate.getForObject(uri, Consultation.class);
//
//			// Give a new title to the page
//			pageTitle = "Edit Consultation";
//		}
//
//		// Attach value to pass to front end
//		model.addAttribute("Consultation", consultation);
//		model.addAttribute("pageTitle",pageTitle);
//
//		return "consultationinfo";
//	}
}
