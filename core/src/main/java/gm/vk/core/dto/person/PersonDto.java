package gm.vk.core.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.core.dto.person.role.PersonRoleDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

public class PersonDto {

    public PersonDto() {
    }

    public PersonDto(Integer id, ContactsDto contacts, PersonalDataDto personalData, PersonRoleDto role) {
        this.id = id;
        this.contacts = contacts;
        this.personalData = personalData;
        this.role = role;
    }

    @JsonProperty("PersonId")
    private Integer id;

    private ContactsDto contacts;

    private PersonalDataDto personalData;

    @NotNull(message = "Person's role missed")
    private PersonRoleDto role;

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
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(contacts)
                .append(personalData)
                .append(role)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("contacts", contacts)
                .append("personalData", personalData)
                .append("role", role)
                .toString();
    }
}