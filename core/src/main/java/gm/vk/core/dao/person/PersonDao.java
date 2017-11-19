package gm.vk.core.dao.person;

import gm.vk.core.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Integer> {

  /**
   * Gets all persons
   *
   * @return {@link List<Person>}
   */
  List<Person> findAll();

  /**
   * Gets the Person by id
   *
   * @param id - the id
   * @return {@link Person}
   */
  Person findOne(final Integer id);

  /**
   * Saves person
   *
   * @param person - the semester
   * @return {@link Person}
   */
  Person save(final Person person);

  /**
   * Deletes person
   *
   * @param person - the semester
   */
  void delete(final Person person);

  /**
   * Deletes the person by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
