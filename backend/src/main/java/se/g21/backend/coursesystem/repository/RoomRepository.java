package se.g21.backend.coursesystem.repository;

import se.g21.backend.coursesystem.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findById(long id);
}
