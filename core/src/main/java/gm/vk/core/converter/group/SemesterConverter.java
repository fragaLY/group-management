package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("semesterConverter")
public class SemesterConverter implements Function<Semester, SemesterDto> {

  @Override
  public SemesterDto apply(Semester semester) {
    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new SemesterDto(
        semester.getId(),
        semester.getSemester(),
        semester.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
