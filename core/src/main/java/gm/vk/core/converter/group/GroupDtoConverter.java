package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.domain.group.course.Course;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.GroupDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("groupDtoConverter")
public class GroupDtoConverter implements Function<GroupDto, Group> {

  @Override
  public Group apply(GroupDto group) {
    return new Group.Builder()
        .setName(group.getName())
        .setId(group.getId())
        .setCourse(new Course(group.getCourse().getId(), group.getCourse().getCourse()))
        .setFaculty(new Faculty(group.getFaculty().getId(), group.getFaculty().getFaculty()))
        .setSemester(new Semester(group.getSemester().getId(), group.getSemester().getSemester()))
        .build();
  }
}
