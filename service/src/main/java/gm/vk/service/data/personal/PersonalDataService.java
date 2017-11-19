package gm.vk.service.data.personal;

import gm.vk.core.dto.data.personal.PersonalDataDto;

import java.util.List;

public interface PersonalDataService {

  List<PersonalDataDto> findAll();

  PersonalDataDto findOne(final Integer id);

  PersonalDataDto save(final PersonalDataDto personalData);

  void delete(final PersonalDataDto personalData);

  void delete(final Integer id);
}
