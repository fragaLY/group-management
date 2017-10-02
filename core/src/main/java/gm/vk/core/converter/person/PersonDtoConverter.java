package gm.vk.core.converter.person;

import gm.vk.core.converter.data.contacts.ContactsDtoConverter;
import gm.vk.core.converter.data.personal.PersonalDataDtoConverter;
import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.converter.person.role.PersonRoleDtoConverter;
import gm.vk.core.converter.subject.SubjectDtoConverter;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personDtoConverter")
public class PersonDtoConverter implements Function<PersonDto, Person> {

    @Autowired
    private ContactsDtoConverter contactsDtoConverter;

    @Autowired
    private PersonalDataDtoConverter personalDataDtoConverter;

    @Autowired
    private PersonRoleDtoConverter personRoleDtoConverter;

    @Autowired
    private SubjectDtoConverter subjectDtoConverter;

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    public Person apply(@NotNull final PersonDto personDto) {
        return new Person.Builder()
                .setId(personDto.getId())
                .setRole(personRoleDtoConverter.apply(personDto.getRole()))
                .setPersonalData(personalDataDtoConverter.apply(personDto.getPersonalData()))
                .setContacts(contactsDtoConverter.apply(personDto.getContacts()))
                .setSubjects(personDto.getSubjects().stream().map(subjectDtoConverter).collect(Collectors.toSet()))
                .setGroup(groupDtoConverter.apply(personDto.getGroup()))
                .build();
    }
}