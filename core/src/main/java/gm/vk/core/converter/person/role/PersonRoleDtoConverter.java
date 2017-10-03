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

@Component("personRoleDtoConverter")
public class PersonRoleDtoConverter implements Function<PersonRoleDto, PersonRole> {

  @Override
  public PersonRole apply(@NotNull final PersonRoleDto personRoleDto) {
    final CustomPersonDtoConverter customPersonDtoConverter = new CustomPersonDtoConverter();
    final Set<Person> persons =
        personRoleDto
            .getPersons()
            .stream()
            .map(customPersonDtoConverter)
            .collect(Collectors.toSet());

    return new PersonRole(personRoleDto.getId(), personRoleDto.getRole(), persons);
  }

  private class CustomPersonDtoConverter implements Function<PersonDto, Person> {

    @Override
    public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getId()).build();
    }
  }
}
