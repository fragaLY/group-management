package gm.vk.service.person.impl;

import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.dao.person.PersonDao;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.exceptions.person.PersonNotFoundException;
import gm.vk.service.person.PersonService;
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

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<PersonDto> findAll() {
        return personDao.findAll().stream().filter(Objects::nonNull).map(personConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonDto findOne(@NotNull final Integer id) {

        final Optional<Person> person = Optional.ofNullable(personDao.findOne(id));

        if (person.isPresent()) {
            return personConverter.apply(person.get());
        } else {
            throw new PersonNotFoundException("Person was not found");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PersonDto save(@NotNull final PersonDto person) {
        personDao.save(personDtoConverter.apply(person));
        return person;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final PersonDto person) {
        personDao.delete(personDtoConverter.apply(person));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        personDao.delete(id);
    }
}