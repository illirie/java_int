package homework.hw4.service;

import homework.hw4.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomService extends CrudRepository<Room, Integer> {
}
