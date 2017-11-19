package gm.vk.core.converter.group.course;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.course.CourseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("courseConverter")
public class CourseConverter implements Function<Course, CourseDto> {

  private static final Logger LOG = LoggerFactory.getLogger(CourseConverter.class);

  /**
   * Converts {@link Course} to {@link CourseDto}
   *
   * @param course - the {@link Course}
   * @return {@link CourseDto}
   */
  @Override
  public CourseDto apply(@NotNull final Course course) {

    LOG.info("Converts Course [{}] to CourseDto", course);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new CourseDto(
        course.getId(),
        course.getCourse(),
        course.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

      @Override
      public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
