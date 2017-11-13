package gm.vk.service.subject.examination.type.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.subject.examination.type.ExaminationTypeConverter;
import gm.vk.core.converter.subject.examination.type.ExaminationTypeDtoConverter;
import gm.vk.core.dao.subject.examination.type.ExaminationTypeDao;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import gm.vk.exceptions.subject.examination.type.ExaminationTypeNotFoundException;
import gm.vk.service.subject.examination.type.ExaminationTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("examinationTypeService") public class ExaminationTypeServiceImpl implements ExaminationTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationTypeServiceImpl.class);

  @Autowired private ExaminationTypeDao examinationTypeDao;

  @Autowired private ExaminationTypeConverter examinationTypeConverter;

  @Autowired private ExaminationTypeDtoConverter examinationTypeDtoConverter;

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public List<ExaminationTypeDto> findAll() {

    LOG.info("Gets all ExaminationTypes");

    return examinationTypeDao.findAll().stream().filter(Objects::nonNull).map(examinationTypeConverter).collect(
        Collectors.toList());
  }

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public ExaminationTypeDto findOne(
      @NotNull final Integer id) {

    LOG.info("Gets ExaminationType by id [{}]", id);

    final Optional<ExaminationType> examinationType = Optional.ofNullable(examinationTypeDao.findOne(id));

    if (examinationType.isPresent()) {
      return examinationTypeConverter.apply(examinationType.get());
    } else {
      LOG.warn("ExaminationType with id [{}] was not found", id);
      throw new ExaminationTypeNotFoundException("ExaminationType was not found");
    }
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public ExaminationTypeDto save(
      @NotNull final ExaminationTypeDto examinationTypeDto) {
    final ExaminationType savedExaminationType = examinationTypeDao.save(examinationTypeDtoConverter.apply(
        examinationTypeDto));
    LOG.info("ExaminationType [{}] had been saved", examinationTypeDto);
    return examinationTypeConverter.apply(savedExaminationType);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final ExaminationTypeDto examinationTypeDto) {
    examinationTypeDao.delete(examinationTypeDtoConverter.apply(examinationTypeDto));
    LOG.info("ExaminationType [{}] had been deleted", examinationTypeDto);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final Integer id) {
    examinationTypeDao.delete(id);
    LOG.info("ExaminationType with id [{}] had been deleted", id);
  }
}
