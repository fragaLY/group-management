package gm.vk.core.converter.person.role;

import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personRoleConverter")
public class PersonRoleConverter implements Function<PersonRole, PersonRoleDto>{

    @Autowired
    private PersonConverter personConverter;

    @Override
    public PersonRoleDto apply(@NotNull final PersonRole personRole) {

        final List<PersonDto> personDtos = personRole.getPersons().stream().map(personConverter).collect(Collectors.toList());

        return new PersonRoleDto(personRole.getId(), personRole.getRole(), personDtos);
    }
}