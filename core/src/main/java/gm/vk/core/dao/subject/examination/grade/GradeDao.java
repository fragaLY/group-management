package gm.vk.core.dao.subject.examination.grade;

import java.util.List;

import gm.vk.core.domain.subject.examination.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeDao extends JpaRepository<Grade, Integer> {

  /**
   * Gets all grades
   *
   * @return {@link List<Grade>}
   */
  List<Grade> findAll();

  /**
   * Gets the Grade by id
   *
   * @param id - the id
   * @return {@link Grade}
   */
  Grade findOne(final Integer id);

  /**
   * Saves Grade
   *
   * @param grade - the grade
   * @return {@link Grade}
   */
  Grade save(final Grade grade);

  /**
   * Deletes grade
   *
   * @param grade - the grade
   */
  void delete(final Grade grade);

  /**
   * Deletes the grade by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
