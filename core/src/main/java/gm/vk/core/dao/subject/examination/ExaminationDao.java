package gm.vk.core.dao.subject.examination;

import java.util.List;

import gm.vk.core.domain.subject.examination.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationDao extends JpaRepository<Examination, Integer> {

  /**
   * Gets all Examinations
   *
   * @return {@link List<Examination>}
   */
  List<Examination> findAll();

  /**
   * Gets the Examination by id
   *
   * @param id - the id
   * @return {@link Examination}
   */
  Examination findOne(final Integer id);

  /**
   * Saves Examination
   *
   * @param examination - the examination
   * @return {@link Examination}
   */
  Examination save(final Examination examination);

  /**
   * Deletes examination
   *
   * @param examination - the examination
   */
  void delete(final Examination examination);

  /**
   * Deletes the examination by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
