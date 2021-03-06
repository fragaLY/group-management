package gm.vk.core.converter.person.role;

import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personRoleConverter")
public class PersonRoleConverter implements Function<PersonRole, PersonRoleDto> {

  @Override
  public PersonRoleDto apply(@NotNull final PersonRole personRole) {
    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();
    final Set<PersonDto> personDtos =
        personRole.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet());

    return new PersonRoleDto(personRole.getId(), personRole.getRole(), personDtos);
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override
    public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }
}
