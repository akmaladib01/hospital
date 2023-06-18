package my.project.dad.hospital.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultation")
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "consultation_id")
	private int consultation_id;
	
	@Column (name = "consultation_time")
	private Time consultation_time;
	
	@Column (name = "consultation_details")
	private String consultation_details;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public int getConsultation_id() {
		return consultation_id;
	}

	public void setConsultation_id(int consultation_id) {
		this.consultation_id = consultation_id;
	}

	public Time getConsultation_time() {
		return consultation_time;
	}

	public void setConsultation_time(Time consultation_time) {
		this.consultation_time = consultation_time;
	}

	public String getConsultation_details() {
		return consultation_details;
	}

	public void setConsultation_details(String consultation_details) {
		this.consultation_details = consultation_details;
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
}