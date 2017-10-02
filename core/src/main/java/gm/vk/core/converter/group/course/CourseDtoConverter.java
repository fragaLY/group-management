package gm.vk.core.converter.group.course;

import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.course.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("courseDtoConverter")
public class CourseDtoConverter implements Function<CourseDto, Course> {

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    public Course apply(CourseDto courseDto) {
        return new Course(courseDto.getId(), courseDto.getCourse(), courseDto.getGroups().stream().map(groupDtoConverter).collect(Collectors.toSet()));
    }
}