package my.project.dad.hospital.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import my.project.dad.hospital.model.Room;

@Controller
public class RoomMenuController {

	@GetMapping("/room/list")
	public String getRoom(Model model) {

		// The URI for GET Room types
		String uri = "http://localhost:8080/hospital/api/room";

		// Get a list of Room types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Room[]> response = restTemplate.getForEntity(uri, Room[].class);

		// Parse JSON data to an array of objects
		Room[] rooms = response.getBody();

		// Convert the array to a list
		List<Room> roomList = Arrays.asList(rooms);

		// Attach the list to the model as an attribute
		model.addAttribute("room", roomList);

		return "room";
	}
}
