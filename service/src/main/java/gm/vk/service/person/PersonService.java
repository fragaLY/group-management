package gm.vk.service.person;

import gm.vk.core.dto.person.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> findAll();

    PersonDto findOne(final Integer id);

    PersonDto save(final PersonDto person);

    void delete(final PersonDto person);

    void delete(final Integer id);
}