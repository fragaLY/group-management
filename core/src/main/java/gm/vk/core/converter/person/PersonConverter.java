package gm.vk.core.converter.person;

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
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("personConverter")
public class PersonConverter implements Function<Person, PersonDto> {

  @Override
  public PersonDto apply(@NotNull final Person person) {
    final CustomSubjectConverter customSubjectConverter = new CustomSubjectConverter();

    return new PersonDto.Builder()
        .setId(person.getId())
        .setRole(new CustomPersonRoleConverter().apply(person.getRole()))
        .setPersonalData(new CustomPersonalDataConverter().apply(person.getPersonalData()))
        .setContacts(new CustomContactConverter().apply(person.getContacts()))
        .setSubjects(
            person.getSubjects().stream().map(customSubjectConverter).collect(Collectors.toSet()))
        .setGroup(new CustomGroupConverter().apply(person.getGroup()))
        .build();
  }

  private class CustomContactConverter implements Function<Contacts, ContactsDto> {

    @Override
    public ContactsDto apply(Contacts contacts) {
      return new ContactsDto.Builder()
          .setId(contacts.getId())
          .setSkype(contacts.getSkype())
          .setPhone(contacts.getPhone())
          .setEmail(contacts.getEmail())
          .build();
    }
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }

  private class CustomPersonRoleConverter implements Function<PersonRole, PersonRoleDto> {

    @Override
    public PersonRoleDto apply(PersonRole personRole) {
      return new PersonRoleDto(personRole.getId(), personRole.getRole());
    }
  }

  private class CustomPersonalDataConverter implements Function<PersonalData, PersonalDataDto> {

    @Override
    public PersonalDataDto apply(PersonalData personalData) {
      return new PersonalDataDto(
          personalData.getId(), personalData.getFirstName(), personalData.getSecondName());
    }
  }

  private class CustomSubjectConverter implements Function<Subject, SubjectDto> {

    @Override
    public SubjectDto apply(Subject subject) {
      return new SubjectDto(subject.getId(), subject.getName());
    }
  }
}
