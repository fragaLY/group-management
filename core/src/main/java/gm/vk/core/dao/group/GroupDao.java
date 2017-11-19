package gm.vk.core.dao.group;

import gm.vk.core.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupDao extends JpaRepository<Group, Integer> {

  /**
   * Gets all groups
   *
   * @return {@link List<Group>}
   */
  List<Group> findAll();

  /**
   * Gets the group by id
   *
   * @param id - the id
   * @return {@link Group}
   */
  Group findOne(final Integer id);

  /**
   * Saves group
   *
   * @param group - the group
   * @return {@link Group}
   */
  Group save(final Group group);

  /**
   * Deletes the group
   *
   * @param group - the group
   */
  void delete(final Group group);

  /**
   * Deletes the group by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
