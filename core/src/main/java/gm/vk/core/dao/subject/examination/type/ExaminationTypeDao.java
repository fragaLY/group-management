package gm.vk.core.dao.subject.examination.type;

import java.util.List;

import gm.vk.core.domain.subject.examination.type.ExaminationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationTypeDao extends JpaRepository<ExaminationType, Integer> {

  /**
   * Gets all ExaminationTypes
   *
   * @return {@link List<ExaminationType>}
   */
  List<ExaminationType> findAll();

  /**
   * Gets the ExaminationType by id
   *
   * @param id - the id
   * @return {@link ExaminationType}
   */
  ExaminationType findOne(final Integer id);

  /**
   * Saves ExaminationType
   *
   * @param type - the type
   * @return {@link ExaminationType}
   */
  ExaminationType save(final ExaminationType type);

  /**
   * Deletes ExaminationType
   *
   * @param type - the type
   */
  void delete(final ExaminationType type);

  /**
   * Deletes the ExaminationType by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
