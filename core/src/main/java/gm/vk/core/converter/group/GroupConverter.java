package gm.vk.core.converter.group;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("groupConverter")
public class GroupConverter implements Function<Group, GroupDto> {

  private static final Logger LOG = LoggerFactory.getLogger(GroupConverter.class);

  @Override public GroupDto apply(@NotNull final Group group) {

    LOG.info("Converts Group [{}] to GroupDto", group);

    return new GroupDto.Builder()
        .setName(group.getName())
        .setId(group.getId())
        .setCourse(new CourseDto(group.getCourse().getId(), group.getCourse().getCourse()))
        .setFaculty(new FacultyDto(group.getFaculty().getId(), group.getFaculty().getFaculty()))
        .setSemester(
            new SemesterDto(group.getSemester().getId(), group.getSemester().getSemester()))
        .build();
  }
}
