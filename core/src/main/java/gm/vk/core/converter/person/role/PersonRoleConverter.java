package gm.vk.core.converter.person.role;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("personRoleConverter")
public class PersonRoleConverter implements Function<PersonRole, PersonRoleDto> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonRoleConverter.class);

  /**
   * Converts {@link PersonRole} to {@link PersonRoleDto}
   *
   * @param personRole - the {@link PersonRole}
   * @return {@link PersonRoleDto}
   */
  @Override public PersonRoleDto apply(@NotNull final PersonRole personRole) {

    LOG.info("Converts PersonRole [{}] to PersonRoleDto", personRole);

    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();
    final Set<PersonDto> personDtos = personRole.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet());

    return new PersonRoleDto(personRole.getId(), personRole.getRole(), personDtos);
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override
    public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }
}
