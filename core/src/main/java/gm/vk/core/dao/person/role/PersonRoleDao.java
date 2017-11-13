package gm.vk.core.dao.person.role;

import java.util.List;

import gm.vk.core.domain.person.role.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRoleDao extends JpaRepository<PersonRole, Integer> {

  List<PersonRole> findAll();

  PersonRole findOne(final Integer id);

  PersonRole save(final PersonRole role);

  void delete(final PersonRole role);

  void delete(final Integer id);
}
