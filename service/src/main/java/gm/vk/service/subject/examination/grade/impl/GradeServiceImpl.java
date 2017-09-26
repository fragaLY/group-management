package gm.vk.service.subject.examination.grade.impl;

import gm.vk.core.converter.subject.examination.grade.GradeConverter;
import gm.vk.core.converter.subject.examination.grade.GradeDtoConverter;
import gm.vk.core.dao.subject.examination.grade.GradeDao;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.exceptions.subject.examination.grade.GradeNotFoundException;
import gm.vk.service.subject.examination.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private GradeConverter gradeConverter;

    @Autowired
    private GradeDtoConverter gradeDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<GradeDto> findAll() {
        return gradeDao.findAll().stream().filter(Objects::nonNull).map(gradeConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public GradeDto findOne(@NotNull final Integer id) {

        final Optional<Grade> grade = Optional.ofNullable(gradeDao.findOne(id));

        if (grade.isPresent()) {
            return gradeConverter.apply(grade.get());
        } else {
            throw new GradeNotFoundException("Grade was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public GradeDto save(@NotNull final GradeDto grade) {
        gradeDao.save(gradeDtoConverter.apply(grade));
        return grade;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final GradeDto grade) {
        gradeDao.delete(gradeDtoConverter.apply(grade));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        gradeDao.delete(id);
    }

}