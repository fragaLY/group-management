package gm.vk.core.dao.person.role;

import java.util.List;

import gm.vk.core.domain.person.role.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRoleDao extends JpaRepository<PersonRole, Integer> {

  /**
   * Gets all PersonRoles
   *
   * @return {@link List<PersonRole>}
   */
  List<PersonRole> findAll();

  /**
   * Gets the PersonRole by id
   *
   * @param id - the id
   * @return {@link PersonRole}
   */
  PersonRole findOne(final Integer id);

  /**
   * Saves role
   *
   * @param role - the semester
   * @return {@link PersonRole}
   */
  PersonRole save(final PersonRole role);

  /**
   * Deletes role
   *
   * @param role - the role
   */
  void delete(final PersonRole role);

  /**
   * Deletes the role by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
