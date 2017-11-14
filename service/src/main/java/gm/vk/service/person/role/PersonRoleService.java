package gm.vk.service.person.role;

import java.util.List;

import gm.vk.core.dto.person.role.PersonRoleDto;

public interface PersonRoleService {

  List<PersonRoleDto> findAll();

  PersonRoleDto findOne(final Integer id);

  PersonRoleDto save(final PersonRoleDto role);

  void delete(final PersonRoleDto role);

  void delete(final Integer id);
}
