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
@Table(name = "prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescription_id")
	private int prescription_id;
	
	@Column(name = "prescription_details")
	private String prescription_details;
	
	@OneToOne
	@JoinColumn(name = "consultation_id")
	private Consultation consultation;
	
	public int getPrescription_id() {
		return prescription_id;
	}
	
	public void setPrescription_id(int prescription_id) {
		this.prescription_id = prescription_id;
	}
	
	public String getPrescription_details() {
		return prescription_details;
	}
	
	public void setPrescription_details(String prescription_details) {
		this.prescription_details = prescription_details;
	}
	
	public Consultation getConsultation() {
		return consultation;
	}
	
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
}
