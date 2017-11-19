package gm.vk.core.converter.group.faculty;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("facultyDtoConverter")
public class FacultyDtoConverter implements Function<FacultyDto, Faculty> {

  private static final Logger LOG = LoggerFactory.getLogger(FacultyDtoConverter.class);

  /**
   * Converts {@link FacultyDto} to {@link Faculty}
   *
   * @param facultyDto - the {@link FacultyDto}
   * @return {@link Faculty}
   */
  @Override
  public Faculty apply(@NotNull final FacultyDto facultyDto) {

    LOG.info("Converts FacultyDto [{}] to Faculty", facultyDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Faculty(
        facultyDto.getFacultyId(),
        facultyDto.getFaculty(),
        facultyDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

      @Override
      public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getGroupId()).setName(group.getName()).build();
    }
  }
}
