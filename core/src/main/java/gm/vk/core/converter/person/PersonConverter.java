package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsConverter;
import gm.vk.core.converter.data.personal.PersonalDataConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("personConverter")
public class PersonConverter implements Function<Person, PersonDto> {

    @Autowired
    private ContactsConverter contactsConverter;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    @Override
    public PersonDto apply(final Person person) {
        return new PersonDto.Builder()
                .setId(person.getId())
                .setLogin(person.getLogin())
                .setPassword(person.getPassword())
                .setContacts(contactsConverter.apply(person.getContacts()))
                .setPersonalData(personalDataConverter.apply(person.getPersonalData()))
                .build();
    }

}