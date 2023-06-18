package my.project.dad.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.project.dad.hospital.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
