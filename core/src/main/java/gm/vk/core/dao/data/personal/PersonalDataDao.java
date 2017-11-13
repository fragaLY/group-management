package gm.vk.core.dao.data.personal;

import java.util.List;

import gm.vk.core.domain.data.personal.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataDao extends JpaRepository<PersonalData, Integer> {

  /**
   * Gets all personal data
   *
   * @return {@link List<PersonalData>}
   */
  List<PersonalData> findAll();

  /**
   * Gets the personal data by id
   *
   * @param id - the id
   * @return {@link PersonalData}
   */
  PersonalData findOne(final Integer id);

  /**
   * Saves the personal data
   *
   * @param data - the personal data
   * @return {@link PersonalData}
   */
  PersonalData save(final PersonalData data);

  /**
   * Deletes personal data
   *
   * @param data - personal data
   */
  void delete(final PersonalData data);

  /**
   * Deletes personal data by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
