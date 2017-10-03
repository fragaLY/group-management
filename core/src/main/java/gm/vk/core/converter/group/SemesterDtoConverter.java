package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("semesterDtoConverter")
public class SemesterDtoConverter implements Function<SemesterDto, Semester> {

  @Override
  public Semester apply(SemesterDto semesterDto) {
    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Semester(
        semesterDto.getId(),
        semesterDto.getSemester(),
        semesterDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
