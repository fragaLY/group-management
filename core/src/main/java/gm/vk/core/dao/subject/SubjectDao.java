package gm.vk.core.dao.subject;

import gm.vk.core.domain.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject, Integer> {

  /**
   * Gets all Subjects
   *
   * @return {@link List<Subject>}
   */
  List<Subject> findAll();

  /**
   * Gets the Subject by id
   *
   * @param id - the id
   * @return {@link Subject}
   */
  Subject findOne(final Integer id);

  /**
   * Saves subject
   *
   * @param subject - the subject
   * @return {@link Subject}
   */
  Subject save(final Subject subject);

  /**
   * Deletes subject
   *
   * @param subject - the subject
   */
  void delete(final Subject subject);

  /**
   * Deletes the subject by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
