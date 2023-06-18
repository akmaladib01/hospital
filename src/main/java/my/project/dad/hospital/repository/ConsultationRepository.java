package my.project.dad.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.project.dad.hospital.model.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

}
