package homework.hw4.service;

import homework.hw4.model.Banned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedService extends CrudRepository<Banned, Integer> {
}
