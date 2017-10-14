package gm.vk.service.group.impl;

import gm.vk.core.converter.group.SemesterConverter;
import gm.vk.core.converter.group.SemesterDtoConverter;
import gm.vk.core.dao.group.SemesterDao;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.SemesterDto;
import gm.vk.exceptions.group.SemesterNotFoundException;
import gm.vk.service.group.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("semesterService")
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterDao semesterDao;

    @Autowired
    private SemesterConverter semesterConverter;

    @Autowired
    private SemesterDtoConverter semesterDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<SemesterDto> findAll() {
        return semesterDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(semesterConverter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SemesterDto findOne(@NotNull final Integer id) {
        final Optional<Semester> semester = Optional.ofNullable(semesterDao.findOne(id));

        if (semester.isPresent()) {
            return semesterConverter.apply(semester.get());
        } else {
            throw new SemesterNotFoundException("Semester was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public SemesterDto save(@NotNull final SemesterDto semester) {
        final Semester savedSemester = semesterDao.save(semesterDtoConverter.apply(semester));
        return semesterConverter.apply(savedSemester);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final SemesterDto semester) {
        semesterDao.delete(semesterDtoConverter.apply(semester));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        semesterDao.delete(id);
    }

}