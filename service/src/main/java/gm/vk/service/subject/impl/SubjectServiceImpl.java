package gm.vk.service.subject.impl;

import gm.vk.core.converter.subject.SubjectConverter;
import gm.vk.core.converter.subject.SubjectDtoConverter;
import gm.vk.core.dao.subject.SubjectDao;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import gm.vk.exceptions.subject.SubjectNotFoundException;
import gm.vk.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private SubjectDtoConverter subjectDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<SubjectDto> findAll() {
        return subjectDao.findAll().stream().filter(Objects::nonNull).map(subjectConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SubjectDto findOne(@NotNull final Integer id) {
        final Optional<Subject> subject = Optional.ofNullable(subjectDao.findOne(id));

        if (subject.isPresent()) {
            return subjectConverter.apply(subject.get());
        } else {
            throw new SubjectNotFoundException("Subject was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SubjectDto save(@NotNull final SubjectDto subject) {
        subjectDao.save(subjectDtoConverter.apply(subject));
        return subject;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final SubjectDto subject) {
        subjectDao.delete(subjectDtoConverter.apply(subject));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        subjectDao.delete(id);
    }
}