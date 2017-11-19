package gm.vk.service.group.course.impl;

import gm.vk.core.converter.group.course.CourseConverter;
import gm.vk.core.converter.group.course.CourseDtoConverter;
import gm.vk.core.dao.group.course.CourseDao;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.exceptions.group.course.CourseNotFoundException;
import gm.vk.service.group.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseConverter courseConverter;

    @Autowired
    private CourseDtoConverter courseDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<CourseDto> findAll() {

        LOG.info("Gets all Courses");

        return courseDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(courseConverter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public CourseDto findOne(@NotNull final Integer id) {

        LOG.info("Gets Course by id [{}]", id);

        final Optional<Course> course = Optional.ofNullable(courseDao.findOne(id));

        if (course.isPresent()) {
            return courseConverter.apply(course.get());
        } else {
            LOG.warn("Course with id [{}] was not found", id);
            throw new CourseNotFoundException("Course was not found");
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public CourseDto save(@NotNull final CourseDto course) {
        final Course savedCourse = courseDao.save(courseDtoConverter.apply(course));
        LOG.info("Course [{}] had been saved", course);
        return courseConverter.apply(savedCourse);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final CourseDto course) {
        courseDao.delete(courseDtoConverter.apply(course));
        LOG.info("Course [{}] had been deleted", course);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        courseDao.delete(id);
        LOG.info("Course with id [{}] had been deleted", id);
    }
}
