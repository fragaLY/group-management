package gm.vk.core.dao.person;

import gm.vk.core.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Integer> {

  List<Person> findAll();

  Person findOne(final Integer id);

  Person save(final Person person);

  void delete(final Person person);

  void delete(final Integer id);
}
