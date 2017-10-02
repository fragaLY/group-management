package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsConverter;
import gm.vk.core.converter.data.personal.PersonalDataConverter;
import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.converter.person.role.PersonRoleConverter;
import gm.vk.core.converter.subject.SubjectConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personConverter")
public class PersonConverter implements Function<Person, PersonDto> {

    @Autowired
    private ContactsConverter contactsConverter;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    @Autowired
    private PersonRoleConverter personRoleConverter;

    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public PersonDto apply(@NotNull final Person person) {
        return new PersonDto.Builder()
                .setId(person.getId())
                .setRole(personRoleConverter.apply(person.getRole()))
                .setPersonalData(personalDataConverter.apply(person.getPersonalData()))
                .setContacts(contactsConverter.apply(person.getContacts()))
                .setSubjects(person.getSubjects().stream().map(subjectConverter).collect(Collectors.toSet()))
                .setGroup(groupConverter.apply(person.getGroup()))
                .build();
    }

}