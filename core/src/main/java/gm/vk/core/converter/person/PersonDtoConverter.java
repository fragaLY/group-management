package gm.vk.core.converter.person;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import gm.vk.core.dto.subject.SubjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("personDtoConverter")
public class PersonDtoConverter implements Function<PersonDto, Person> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonDtoConverter.class);

  @Override
  public Person apply(@NotNull final PersonDto personDto) {

    LOG.info("Converts PersonDto [{}] to Person", personDto);

    final CustomSubjectConverter customSubjectConverter = new CustomSubjectConverter();

    return new Person.Builder()
        .setId(personDto.getId())
        .setRole(new CustomPersonRoleConverter().apply(personDto.getRole()))
        .setPersonalData(new CustomPersonalDataConverter().apply(personDto.getPersonalData()))
        .setContacts(new CustomContactConverter().apply(personDto.getContacts()))
        .setSubjects(
            personDto
                .getSubjects()
                .stream()
                .map(customSubjectConverter)
                .collect(Collectors.toSet()))
        .setGroup(new CustomGroupConverter().apply(personDto.getGroup()))
        .build();
  }

  private class CustomContactConverter implements Function<ContactsDto, Contacts> {

    @Override
    public Contacts apply(ContactsDto contacts) {
      return new Contacts.Builder()
          .setId(contacts.getId())
          .setSkype(contacts.getSkype())
          .setPhone(contacts.getPhone())
          .setEmail(contacts.getEmail())
          .build();
    }
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }

  private class CustomPersonRoleConverter implements Function<PersonRoleDto, PersonRole> {

    @Override
    public PersonRole apply(PersonRoleDto personRole) {
      return new PersonRole(personRole.getId(), personRole.getRole());
    }
  }

  private class CustomPersonalDataConverter implements Function<PersonalDataDto, PersonalData> {

    @Override
    public PersonalData apply(PersonalDataDto personalData) {
      return new PersonalData(
          personalData.getId(), personalData.getFirstName(), personalData.getSecondName());
    }
  }

  private class CustomSubjectConverter implements Function<SubjectDto, Subject> {

    @Override
    public Subject apply(SubjectDto subject) {
      return new Subject(subject.getId(), subject.getName());
    }
  }
}
