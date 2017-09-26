package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsConverter;
import gm.vk.core.converter.data.personal.PersonalDataConverter;
import gm.vk.core.converter.person.role.PersonRoleConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("personConverter")
public class PersonConverter implements Function<Person, PersonDto> {

    @Autowired
    private ContactsConverter contactsConverter;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    @Autowired
    private PersonRoleConverter personRoleConverter;

    @Override
    public PersonDto apply(@NotNull final Person person) {
        return new PersonDto(person.getId(),
                contactsConverter.apply(person.getContacts()),
                personalDataConverter.apply(person.getPersonalData()),
                personRoleConverter.apply(person.getRole()));
    }

}