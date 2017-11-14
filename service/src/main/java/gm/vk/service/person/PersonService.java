package gm.vk.service.person;

import java.util.List;

import gm.vk.core.dto.person.PersonDto;

public interface PersonService {

  List<PersonDto> findAll();

  PersonDto findOne(final Integer id);

  PersonDto save(final PersonDto person);

  void delete(final PersonDto person);

  void delete(final Integer id);
}
