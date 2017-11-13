package gm.vk.service.subject.examination.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.dao.subject.examination.ExaminationDao;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.exceptions.subject.examination.ExaminationNotFoundException;
import gm.vk.service.subject.examination.ExaminationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("examinationService") public class ExaminationServiceImpl implements ExaminationService {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationServiceImpl.class);

  @Autowired private ExaminationDao examinationDao;

  @Autowired private ExaminationConverter examinationConverter;

  @Autowired private ExaminationDtoConverter examinationDtoConverter;

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public List<ExaminationDto> findAll() {

    LOG.info("Gets all Examinations");

    return examinationDao.findAll().stream().filter(Objects::nonNull).map(examinationConverter).collect(
        Collectors.toList());
  }

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public ExaminationDto findOne(
      @NotNull final Integer id) {

    LOG.info("Gets Examination by id [{}]", id);

    final Optional<Examination> examination = Optional.ofNullable(examinationDao.findOne(id));

    if (examination.isPresent()) {
      return examinationConverter.apply(examination.get());
    } else {
      LOG.warn("Examination with id [{}] was not found", id);
      throw new ExaminationNotFoundException("Examination was not found");
    }
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public ExaminationDto save(
      @NotNull final ExaminationDto examination) {
    final Examination savedExamination = examinationDao.save(examinationDtoConverter.apply(examination));
    LOG.info("Examination [{}] had been saved", examination);
    return examinationConverter.apply(savedExamination);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final ExaminationDto examination) {
    examinationDao.delete(examinationDtoConverter.apply(examination));
    LOG.info("Examination [{}] had been deleted", examination);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final Integer id) {
    examinationDao.delete(id);
    LOG.info("Examination with id [{}] had been deleted", id);
  }
}
