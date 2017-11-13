package gm.vk.core.converter.group;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.GroupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("groupDtoConverter")
public class GroupDtoConverter implements Function<GroupDto, Group> {

  private static final Logger LOG = LoggerFactory.getLogger(GroupDtoConverter.class);

  @Override public Group apply(@NotNull final GroupDto group) {

    LOG.info("Converts GroupDto [{}] to Group", group);

    return new Group.Builder()
        .setName(group.getName())
        .setId(group.getId())
        .setCourse(new Course(group.getCourse().getId(), group.getCourse().getCourse()))
        .setFaculty(new Faculty(group.getFaculty().getId(), group.getFaculty().getFaculty()))
        .setSemester(new Semester(group.getSemester().getId(), group.getSemester().getSemester()))
        .build();
  }
}
