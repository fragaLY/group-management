package gm.vk.service.person.role.impl;

import gm.vk.core.converter.person.role.PersonRoleConverter;
import gm.vk.core.converter.person.role.PersonRoleDtoConverter;
import gm.vk.core.dao.person.role.PersonRoleDao;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.role.PersonRoleDto;
import gm.vk.exceptions.person.role.PersonRoleNotFoundException;
import gm.vk.service.person.role.PersonRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("personRoleService")
public class PersonRoleServiceImpl implements PersonRoleService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonRoleServiceImpl.class);

  @Autowired private PersonRoleDao personRoleDao;

  @Autowired private PersonRoleConverter personRoleConverter;

  @Autowired private PersonRoleDtoConverter personRoleDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<PersonRoleDto> findAll() {

    LOG.info("Gets all PersonRoles");

        return personRoleDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(personRoleConverter)
                .collect(Collectors.toList());
  }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonRoleDto findOne(@NotNull final Integer id) {

    LOG.info("Gets PersonRole by id [{}]", id);

    final Optional<PersonRole> role = Optional.ofNullable(personRoleDao.findOne(id));

    if (role.isPresent()) {
      return personRoleConverter.apply(role.get());
    } else {
      LOG.warn("PersonRole with id [{}] was not found", id);
      throw new PersonRoleNotFoundException("PersonRole was not found");
    }
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonRoleDto save(@NotNull final PersonRoleDto role) {
    final PersonRole savedPersonRole = personRoleDao.save(personRoleDtoConverter.apply(role));
    LOG.info("PersonRole [{}] had been saved", role);
    return personRoleConverter.apply(savedPersonRole);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final PersonRoleDto role) {
    personRoleDao.delete(personRoleDtoConverter.apply(role));
    LOG.info("PersonRole [{}] had been deleted", role);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
    personRoleDao.delete(id);
    LOG.info("PersonRole with id [{}] had been deleted", id);
  }
}
