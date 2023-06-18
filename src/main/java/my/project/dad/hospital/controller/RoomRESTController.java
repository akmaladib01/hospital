package my.project.dad.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.project.dad.hospital.model.Room;
import my.project.dad.hospital.repository.RoomRepository;

@RestController
@RequestMapping("/api/room")
public class RoomRESTController {

	@Autowired
	private RoomRepository roomRepository;
	
	// GET all Rooms
	@GetMapping
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}
	
	// GET Room by ID
	@GetMapping ("{Room_id}")
	public Room getRoom(@PathVariable long room_id) {
		Room Room = roomRepository.findById(room_id).get();
		return Room;
	}
	
	// POST (insert) new Room
	@PostMapping()
	public Room insertRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}
	
	// PUT (update) existing Room
	@PutMapping()
	public Room updateRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}
	
	// DELETE Room by ID
	@DeleteMapping("{Room_id}")
	public ResponseEntity<HttpStatus> deleteRoom(@PathVariable long room_id) {
		roomRepository.deleteById(room_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
