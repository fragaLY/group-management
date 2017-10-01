package gm.vk.core.converter.person.role;

import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personRoleDtoConverter")
public class PersonRoleDtoConverter implements Function<PersonRoleDto, PersonRole> {

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    public PersonRole apply(@NotNull final PersonRoleDto personRoleDto) {

        final Set<Person> persons = personRoleDto.getPersons().stream().map(personDtoConverter).collect(Collectors.toSet());

        return new PersonRole(personRoleDto.getId(), personRoleDto.getRole(), persons);
    }
}