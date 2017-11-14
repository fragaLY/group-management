package gm.vk.service.data.personal.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.data.personal.PersonalDataConverter;
import gm.vk.core.converter.data.personal.PersonalDataDtoConverter;
import gm.vk.core.dao.data.personal.PersonalDataDao;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.exceptions.data.personal.PersonalDataNotFoundException;
import gm.vk.service.data.personal.PersonalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("personalDataService") public class PersonalDataServiceImpl implements PersonalDataService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonalDataServiceImpl.class);

  @Autowired private PersonalDataDao personalDataDao;

  @Autowired private PersonalDataConverter personalDataConverter;

  @Autowired private PersonalDataDtoConverter personalDataDtoConverter;

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public List<PersonalDataDto> findAll() {

    LOG.info("Gets all PersonalData");

    return personalDataDao.findAll().stream().filter(Objects::nonNull).map(personalDataConverter).collect(
        Collectors.toList());
  }

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public PersonalDataDto findOne(
      @NotNull final Integer id) {

    LOG.info("Gets PersonalData by id [{}]", id);

    final Optional<PersonalData> personalData = Optional.ofNullable(personalDataDao.findOne(id));

    if (personalData.isPresent()) {
      return personalDataConverter.apply(personalData.get());
    } else {
      LOG.warn("PersonalData with id [{}] was not found", id);
      throw new PersonalDataNotFoundException("PersonalData was not found");
    }
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public PersonalDataDto save(
      @NotNull final PersonalDataDto personalData) {
    final PersonalData savedPersonalData = personalDataDao.save(personalDataDtoConverter.apply(personalData));

    LOG.info("PersonalData [{}] had been saved", personalData);

    return personalDataConverter.apply(savedPersonalData);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final PersonalDataDto personalData) {
    personalDataDao.delete(personalDataDtoConverter.apply(personalData));
    LOG.info("PersonalData [{}] had been deleted", personalData);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final Integer id) {
    personalDataDao.delete(id);
    LOG.info("PersonalData with id [{}] had been deleted", id);
  }
}
