package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("groupConverter")
public class GroupConverter implements Function<Group, GroupDto> {

  @Override
  public GroupDto apply(Group group) {
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
