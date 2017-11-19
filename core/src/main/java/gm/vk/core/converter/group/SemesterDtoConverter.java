package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("semesterDtoConverter")
public class SemesterDtoConverter implements Function<SemesterDto, Semester> {

  private static final Logger LOG = LoggerFactory.getLogger(SemesterDtoConverter.class);

  /**
   * Converts {@link SemesterDto} to {@link Semester}
   *
   * @param semesterDto {@link SemesterDto}
   * @return {@link Semester}
   */
  @Override
  public Semester apply(@NotNull final SemesterDto semesterDto) {

    LOG.info("Converts SemesterDto [{}] to Semester", semesterDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new Semester(
        semesterDto.getSemesterId(),
        semesterDto.getSemester(),
        semesterDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

      @Override
      public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getGroupId()).setName(group.getName()).build();
    }
  }
}
