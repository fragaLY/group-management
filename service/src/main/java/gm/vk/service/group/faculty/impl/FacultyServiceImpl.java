package gm.vk.service.group.faculty.impl;

import gm.vk.core.converter.group.faculty.FacultyConverter;
import gm.vk.core.converter.group.faculty.FacultyDtoConverter;
import gm.vk.core.dao.group.faculty.FacultyDao;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.faculty.FacultyDto;
import gm.vk.exceptions.group.faculty.FacultyNotFoundException;
import gm.vk.service.group.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("facultyService")
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private FacultyConverter facultyConverter;

    @Autowired
    private FacultyDtoConverter facultyDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<FacultyDto> findAll() {
        return facultyDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(facultyConverter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public FacultyDto findOne(@NotNull final Integer id) {
        final Optional<Faculty> faculty = Optional.ofNullable(facultyDao.findOne(id));

        if (faculty.isPresent()) {
            return facultyConverter.apply(faculty.get());
        } else {
            throw new FacultyNotFoundException("Faculty was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public FacultyDto save(@NotNull final FacultyDto faculty) {
        final Faculty savedFaculty = facultyDao.save(facultyDtoConverter.apply(faculty));
        return facultyConverter.apply(savedFaculty);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final FacultyDto faculty) {
        facultyDao.delete(facultyDtoConverter.apply(faculty));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        facultyDao.delete(id);
    }
}
