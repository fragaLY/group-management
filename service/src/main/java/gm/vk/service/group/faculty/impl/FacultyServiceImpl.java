package gm.vk.service.group.faculty.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.group.faculty.FacultyConverter;
import gm.vk.core.converter.group.faculty.FacultyDtoConverter;
import gm.vk.core.dao.group.faculty.FacultyDao;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.faculty.FacultyDto;
import gm.vk.exceptions.group.faculty.FacultyNotFoundException;
import gm.vk.service.group.faculty.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("facultyService")
public class FacultyServiceImpl implements FacultyService {

    private static final Logger LOG = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private FacultyConverter facultyConverter;

    @Autowired
    private FacultyDtoConverter facultyDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<FacultyDto> findAll() {

        LOG.info("Gets all Faculties");

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

        LOG.info("Gets Faculty by id [{}]");

        final Optional<Faculty> faculty = Optional.ofNullable(facultyDao.findOne(id));

        if (faculty.isPresent()) {
            return facultyConverter.apply(faculty.get());
        } else {
            LOG.warn("Faculty with id [{}] was not found", id);
            throw new FacultyNotFoundException("Faculty was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public FacultyDto save(@NotNull final FacultyDto faculty) {
        final Faculty savedFaculty = facultyDao.save(facultyDtoConverter.apply(faculty));
        LOG.info("Faculty [{}] had been saved", faculty);
        return facultyConverter.apply(savedFaculty);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final FacultyDto faculty) {
        facultyDao.delete(facultyDtoConverter.apply(faculty));
        LOG.info("Faculty [{}] had been deleted", faculty);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        facultyDao.delete(id);
        LOG.info("Faculty with id [{}] had been deleted", id);
    }
}
