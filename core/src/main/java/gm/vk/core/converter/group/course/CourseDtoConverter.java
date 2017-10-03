package gm.vk.core.converter.group.course;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.course.CourseDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("courseDtoConverter")
public class CourseDtoConverter implements Function<CourseDto, Course> {

  @Override
  public Course apply(CourseDto courseDto) {
    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Course(
        courseDto.getId(),
        courseDto.getCourse(),
        courseDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
