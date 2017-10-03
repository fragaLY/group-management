package gm.vk.core.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import gm.vk.core.dto.subject.SubjectDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class PersonDto {

  @JsonProperty("PersonId")
  private Integer id;
  private ContactsDto contacts;
  private PersonalDataDto personalData;
  @NotNull(message = "Person's role missed")
  private PersonRoleDto role;
  private Set<SubjectDto> subjects;
  private GroupDto group;

  public PersonDto() {}

  private PersonDto(final Builder builder) {
    this.id = builder.id;
    this.contacts = builder.contacts;
    this.personalData = builder.personalData;
    this.role = builder.role;
    this.subjects = builder.subjects;
    this.group = group;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ContactsDto getContacts() {
    return contacts;
  }

  public void setContacts(ContactsDto contacts) {
    this.contacts = contacts;
  }

  public PersonalDataDto getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalDataDto personalData) {
    this.personalData = personalData;
  }

  public PersonRoleDto getRole() {
    return role;
  }

  public void setRole(PersonRoleDto role) {
    this.role = role;
  }

  public Set<SubjectDto> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<SubjectDto> subjects) {
    this.subjects = subjects;
  }

  public GroupDto getGroup() {
    return group;
  }

  public void setGroup(GroupDto group) {
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof PersonDto)) return false;

    PersonDto personDto = (PersonDto) o;

    return new EqualsBuilder()
        .append(id, personDto.id)
        .append(contacts, personDto.contacts)
        .append(personalData, personDto.personalData)
        .append(role, personDto.role)
        .append(subjects, personDto.subjects)
        .append(group, personDto.group)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(contacts)
        .append(personalData)
        .append(role)
        .append(subjects)
        .append(group)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("contacts", contacts)
        .append("personalData", personalData)
        .append("role", role)
        .append("subjects", subjects)
        .append("group", group)
        .toString();
  }

  public static class Builder {

    private Integer id;
    private PersonRoleDto role;
    private ContactsDto contacts;
    private PersonalDataDto personalData;
    private Set<SubjectDto> subjects;
    private GroupDto group;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setRole(PersonRoleDto role) {
      this.role = role;
      return this;
    }

    public Builder setContacts(ContactsDto contacts) {
      this.contacts = contacts;
      return this;
    }

    public Builder setPersonalData(PersonalDataDto personalData) {
      this.personalData = personalData;
      return this;
    }

    public Builder setSubjects(Set<SubjectDto> subjects) {
      this.subjects = subjects;
      return this;
    }

    public Builder setGroup(GroupDto group) {
      this.group = group;
      return this;
    }

    public PersonDto build() {
      return new PersonDto(this);
    }
  }
}
