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

@Component("facultyConverter")
public class FacultyConverter implements Function<Faculty, FacultyDto> {

  private static final Logger LOG = LoggerFactory.getLogger(FacultyConverter.class);

  @Override public FacultyDto apply(@NotNull final Faculty faculty) {

    LOG.info("Converts Faculty [{}] to FacultyDto", faculty);

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
