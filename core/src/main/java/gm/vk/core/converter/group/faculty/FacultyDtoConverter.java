package gm.vk.core.converter.group.faculty;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("facultyDtoConverter")
public class FacultyDtoConverter implements Function<FacultyDto, Faculty> {

  private static final Logger LOG = LoggerFactory.getLogger(FacultyDtoConverter.class);

  @Override public Faculty apply(@NotNull final FacultyDto facultyDto) {

    LOG.info("Converts FacultyDto [{}] to Faculty", facultyDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Faculty(
        facultyDto.getId(),
        facultyDto.getFaculty(),
        facultyDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
