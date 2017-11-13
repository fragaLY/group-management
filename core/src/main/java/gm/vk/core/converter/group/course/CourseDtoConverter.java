package gm.vk.core.converter.group.course;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.course.CourseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("courseDtoConverter")
public class CourseDtoConverter implements Function<CourseDto, Course> {

  private static final Logger LOG = LoggerFactory.getLogger(CourseDtoConverter.class);

  /**
   * Converts {@link CourseDto} to {@link Course}
   *
   * @param courseDto - the {@link CourseDto}
   * @return {@link Course}
   */
  @Override public Course apply(@NotNull final CourseDto courseDto) {

    LOG.info("Converts CourseDto [{}] to Course", courseDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Course(courseDto.getId(), courseDto.getCourse(), courseDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
