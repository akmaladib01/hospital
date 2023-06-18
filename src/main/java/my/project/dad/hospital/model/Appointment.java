package my.project.dad.hospital.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "appointment_id")
	private int appointment_id;
	
	@Column (name = "appointment_date")
	private Date appointment_date;
	
	@Column (name = "appointment_time")
	private Time appointment_time;
	
	@Column (name = "appointment_details")
	private String appointment_details;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@OneToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	public int getAppointment_id() {
		return appointment_id;
	}
	
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	
	public Date getAppointment_date() {
		return appointment_date;
	}
	
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	
	public Time getAppointment_time() {
		return appointment_time;
	}
	
	public void setAppointment_time(Time appointment_time) {
		this.appointment_time = appointment_time;
	}
	
	public String getAppointment_details() {
		return appointment_details;
	}
	
	public void setAppointment_details(String appointment_details) {
		this.appointment_details = appointment_details;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
