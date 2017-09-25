package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsDtoConverter;
import gm.vk.core.converter.data.personal.PersonalDataDtoConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("personDtoConverter")
public class PersonDtoConverter implements Function<PersonDto, Person> {

    @Autowired
    private ContactsDtoConverter contactsDtoConverter;

    @Autowired
    private PersonalDataDtoConverter personalDataDtoConverter;

    @Override
    public Person apply(final PersonDto personDto) {
        return new Person.Builder()
                .setId(personDto.getId())
                .setLogin(personDto.getLogin())
                .setPassword(personDto.getPassword())
                .setContacts(contactsDtoConverter.apply(personDto.getContacts()))
                .setPersonalData(personalDataDtoConverter.apply(personDto.getPersonalData()))
                .build();
    }
}