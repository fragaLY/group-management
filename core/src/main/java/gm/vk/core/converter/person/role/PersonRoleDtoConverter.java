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

@Component("personRoleDtoConverter")
public class PersonRoleDtoConverter implements Function<PersonRoleDto, PersonRole> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonRoleDtoConverter.class);

  @Override
  public PersonRole apply(@NotNull final PersonRoleDto personRoleDto) {

    LOG.info("Converts PersonRoleDto [{}] to PersonRole", personRoleDto);
    
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
