package homework.hw4.service;


import homework.hw4.model.Guest;
import homework.hw4.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestService extends CrudRepository<Guest, Integer> {
    Iterable<Guest> findAll();
    Person findByFirstName(String firstName);
}
