package my.project.dad.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private int patient_id;
	
	@Column(name = "patient_name")
	private String patient_name;
	
	@Column(name = "patient_phone")
	private String patient_phone;
	
	@Column(name = "patient_address")
	private String patient_address;
	
	@Column(name = "patient_age")
	private String patient_age;
	
	@Column(name = "patient_gender")
	private String patient_gender;
	
	public int getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getPatient_name() {
		return patient_name;
	}
	
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	
	public String getPatient_phone() {
		return patient_phone;
	}
	
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	
	public String getPatient_address() {
		return patient_address;
	}
	
	public void setPatient_address(String patient_address) {
		this.patient_address = patient_address;
	}
	
	public String getPatient_age() {
		return patient_age;
	}
	
	public void setPatient_age(String patient_age) {
		this.patient_age = patient_age;
	}
	
	public String getPatient_gender() {
		return patient_gender;
	}
	
	public void setPatient_gender(String patient_gender) {
		this.patient_gender = patient_gender;
	}
}
