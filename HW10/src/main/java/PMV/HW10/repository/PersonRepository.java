package PMV.HW10.repository;

import PMV.HW10.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);
}
