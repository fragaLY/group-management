package gm.vk.core.converter.group;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.group.SemesterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("semesterConverter")
public class SemesterConverter implements Function<Semester, SemesterDto> {

  private static final Logger LOG = LoggerFactory.getLogger(SemesterConverter.class);

  /**
   * Converts {@link Semester} to {@link SemesterDto}
   *
   * @param semester - the {@link SemesterDto}
   * @return {@link Semester}
   */
  @Override public SemesterDto apply(@NotNull final Semester semester) {

    LOG.info("Converts Semester [{}] to SemesterDto", semester);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new SemesterDto(semester.getId(), semester.getSemester(), semester.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}
