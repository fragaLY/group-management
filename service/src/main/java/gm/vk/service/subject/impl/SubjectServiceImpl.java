package gm.vk.service.subject.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.subject.SubjectConverter;
import gm.vk.core.converter.subject.SubjectDtoConverter;
import gm.vk.core.dao.subject.SubjectDao;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import gm.vk.exceptions.subject.SubjectNotFoundException;
import gm.vk.service.subject.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("subjectService") public class SubjectServiceImpl implements SubjectService {

  private static final Logger LOG = LoggerFactory.getLogger(SubjectServiceImpl.class);

  @Autowired private SubjectDao subjectDao;

  @Autowired private SubjectConverter subjectConverter;

  @Autowired private SubjectDtoConverter subjectDtoConverter;

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public List<SubjectDto> findAll() {

    LOG.info("Gets all Subjects");

    return subjectDao.findAll().stream().filter(Objects::nonNull).map(subjectConverter).collect(Collectors.toList());
  }

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public SubjectDto findOne(
      @NotNull final Integer id) {

    LOG.info("Gets Subject by id [{}]", id);

    final Optional<Subject> subject = Optional.ofNullable(subjectDao.findOne(id));

    if (subject.isPresent()) {
      return subjectConverter.apply(subject.get());
    } else {
      LOG.warn("Subject with id [{}] was not found", id);
      throw new SubjectNotFoundException("Subject was not found");
    }
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public SubjectDto save(
      @NotNull final SubjectDto subject) {
    final Subject savedSubject = subjectDao.save(subjectDtoConverter.apply(subject));
    LOG.info("Subject [{}] had been saved", subject);
    return subjectConverter.apply(savedSubject);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final SubjectDto subject) {
    subjectDao.delete(subjectDtoConverter.apply(subject));
    LOG.info("Subject [{}] had been deleted", subject);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final Integer id) {
    subjectDao.delete(id);
    LOG.info("Subject with id [{}] had been deleted", id);
  }
}
