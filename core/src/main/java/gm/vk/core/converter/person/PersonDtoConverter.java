package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsDtoConverter;
import gm.vk.core.converter.data.personal.PersonalDataDtoConverter;
import gm.vk.core.converter.person.role.PersonRoleDtoConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("personDtoConverter")
public class PersonDtoConverter implements Function<PersonDto, Person> {

    @Autowired
    private ContactsDtoConverter contactsDtoConverter;

    @Autowired
    private PersonalDataDtoConverter personalDataDtoConverter;

    @Autowired
    private PersonRoleDtoConverter personRoleDtoConverter;

    @Override
    public Person apply(@NotNull final PersonDto personDto) {
        return new Person(personDto.getId(),
                          personRoleDtoConverter.apply(personDto.getRole()),
                          contactsDtoConverter.apply(personDto.getContacts()),
                          personalDataDtoConverter.apply(personDto.getPersonalData()));
    }
}