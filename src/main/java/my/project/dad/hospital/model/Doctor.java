package my.project.dad.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private int doctor_id;

	@Column(name = "doctor_name")
	private String doctor_name;
	
	@Column(name = "doctor_phone")
	private String doctor_phone;
	
	@Column(name = "doctor_skill")
	private String doctor_skill;
	
	@OneToOne
	@JoinColumn(name = "room_name")
	private Room room;
	
	public int getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public String getDoctor_name() {
		return doctor_name;
	}
	
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	
	public String getDoctor_phone() {
		return doctor_phone;
	}
	
	public void setDoctor_phone(String doctor_phone) {
		this.doctor_phone = doctor_phone;
	}
	
	public String getDoctor_skill() {
		return doctor_skill;
	}
	
	public void setDoctor_skill(String doctor_skill) {
		this.doctor_skill = doctor_skill;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
}
