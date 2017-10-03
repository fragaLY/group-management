package gm.vk.service.subject.examination.impl;

import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.dao.subject.examination.ExaminationDao;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.exceptions.subject.examination.ExaminationNotFoundException;
import gm.vk.service.subject.examination.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("examinationService")
public class ExaminationServiceImpl implements ExaminationService {

  @Autowired private ExaminationDao examinationDao;

  @Autowired private ExaminationConverter examinationConverter;

  @Autowired private ExaminationDtoConverter examinationDtoConverter;

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public List<ExaminationDto> findAll() {
    return examinationDao
        .findAll()
        .stream()
        .filter(Objects::nonNull)
        .map(examinationConverter)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public ExaminationDto findOne(@NotNull final Integer id) {

    final Optional<Examination> examination = Optional.ofNullable(examinationDao.findOne(id));

    if (examination.isPresent()) {
      return examinationConverter.apply(examination.get());
    } else {
      throw new ExaminationNotFoundException("Examination was not found");
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public ExaminationDto save(@NotNull final ExaminationDto examination) {
    examinationDao.save(examinationDtoConverter.apply(examination));
    return examination;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final ExaminationDto examination) {
    examinationDao.delete(examinationDtoConverter.apply(examination));
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final Integer id) {
    examinationDao.delete(id);
  }
}
