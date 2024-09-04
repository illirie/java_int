package homework.hw4.service;

import homework.hw4.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateService extends CrudRepository<Rate, Integer> {
}
