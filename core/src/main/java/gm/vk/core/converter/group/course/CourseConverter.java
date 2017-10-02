package gm.vk.core.converter.group.course;

import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.course.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("courseConverter")
public class CourseConverter implements Function<Course, CourseDto> {

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public CourseDto apply(Course course) {
        return new CourseDto(course.getId(), course.getCourse(), course.getGroups().stream().map(groupConverter).collect(Collectors.toSet()));
    }
}