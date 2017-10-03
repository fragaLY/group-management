package gm.vk.core.converter.group.faculty;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("facultyConverter")
public class FacultyConverter implements Function<Faculty, FacultyDto> {

  @Override
  public FacultyDto apply(Faculty faculty) {
    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new FacultyDto(
        faculty.getId(),
        faculty.getFaculty(),
        faculty.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
