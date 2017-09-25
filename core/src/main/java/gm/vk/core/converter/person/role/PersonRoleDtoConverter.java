package gm.vk.core.converter.person.role;

import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personRoleDtoConverter")
public class PersonRoleDtoConverter implements Function<PersonRoleDto, PersonRole> {

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    public PersonRole apply(final PersonRoleDto personRoleDto) {

        final List<Person> persons = personRoleDto.getPersons().stream().map(personDtoConverter).collect(Collectors.toList());

        return new PersonRole(personRoleDto.getId(), personRoleDto.getRole(), persons);
    }
}