package gm.vk.core.dao.person.role;

import gm.vk.core.domain.person.role.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRoleDao extends JpaRepository<PersonRole, Integer> {

    List<PersonRole> findAll();

    PersonRole findOne(final Integer integer);

    PersonRole save(final PersonRole role);

    void delete(final PersonRole role);

    void delete(final Integer id);
}