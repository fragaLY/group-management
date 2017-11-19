package gm.vk.service.person.impl;

import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.dao.person.PersonDao;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.exceptions.person.PersonNotFoundException;
import gm.vk.service.person.PersonService;
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

@Service("personService")
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

  @Autowired private PersonDao personDao;

  @Autowired private PersonConverter personConverter;

  @Autowired private PersonDtoConverter personDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<PersonDto> findAll() {

    LOG.info("Gets all Persons");

        return personDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(personConverter)
                .collect(Collectors.toList());
  }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonDto findOne(@NotNull final Integer id) {

    LOG.info("Gets Person with id [{}]", id);

    final Optional<Person> person = Optional.ofNullable(personDao.findOne(id));

    if (person.isPresent()) {
      return personConverter.apply(person.get());
    } else {
      LOG.warn("Person with id [{}] was not found", id);
      throw new PersonNotFoundException("Person was not found");
    }
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonDto save(@NotNull final PersonDto person) {
    final Person savedPerson = personDao.save(personDtoConverter.apply(person));
    LOG.info("Person [{}] had been saved", person);
    return personConverter.apply(savedPerson);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final PersonDto person) {
    personDao.delete(personDtoConverter.apply(person));
    LOG.info("Person [{}] had been deleted", person);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
    personDao.delete(id);
    LOG.warn("Person with id [{}] had been deleted", id);
  }
}
