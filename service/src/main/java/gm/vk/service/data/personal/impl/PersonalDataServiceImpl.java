package gm.vk.service.data.personal.impl;

import gm.vk.core.converter.data.personal.PersonalDataConverter;
import gm.vk.core.converter.data.personal.PersonalDataDtoConverter;
import gm.vk.core.dao.data.personal.PersonalDataDao;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.exceptions.data.personal.PersonalDataNotFoundException;
import gm.vk.service.data.personal.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("personalDataService")
public class PersonalDataServiceImpl implements PersonalDataService {

  @Autowired private PersonalDataDao personalDataDao;

  @Autowired private PersonalDataConverter personalDataConverter;

  @Autowired private PersonalDataDtoConverter personalDataDtoConverter;

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public List<PersonalDataDto> findAll() {
    return personalDataDao
        .findAll()
        .stream()
        .filter(Objects::nonNull)
        .map(personalDataConverter)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public PersonalDataDto findOne(@NotNull final Integer id) {
    final Optional<PersonalData> personalData = Optional.ofNullable(personalDataDao.findOne(id));

    if (personalData.isPresent()) {
      return personalDataConverter.apply(personalData.get());
    } else {
      throw new PersonalDataNotFoundException("PersonalData was not found");
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public PersonalDataDto save(@NotNull final PersonalDataDto personalData) {
      final PersonalData savedPersonalData =
              personalDataDao.save(personalDataDtoConverter.apply(personalData));
      return personalDataConverter.apply(savedPersonalData);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final PersonalDataDto personalData) {
    personalDataDao.delete(personalDataDtoConverter.apply(personalData));
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final Integer id) {
    personalDataDao.delete(id);
  }
}
