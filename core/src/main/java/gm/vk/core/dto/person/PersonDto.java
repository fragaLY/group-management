package gm.vk.core.dto.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonDto {

    private static final String LOGIN_REGEXP = "^[a-zA-Z][a-zA-Z0-9_.,-]{5,31}$";

    public PersonDto() {
    }

    private PersonDto(final Builder builder) {
        this.id = builder.id;
        this.contacts = builder.contacts;
        this.personalData = builder.personalData;
        this.login = builder.login;
        this.password = builder.password;
    }

    @JsonProperty("PersonId")
    private Integer id;

    @NotNull
    private ContactsDto contacts;

    @NotNull
    private PersonalDataDto personalData;

    @Pattern(regexp = LOGIN_REGEXP)
    @Size(min = 3, max = 25)
    private String login;

    @JsonIgnore
    private String password;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                .append(login, personDto.login)
                .append(password, personDto.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(contacts)
                .append(personalData)
                .append(login)
                .append(password)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("contacts", contacts)
                .append("personalData", personalData)
                .append("login", login)
                .append("password", password)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private ContactsDto contacts;
        private PersonalDataDto personalData;
        private String login;
        private String password;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setContacts(final ContactsDto contacts) {
            this.contacts = contacts;
            return this;
        }

        public Builder setPersonalData(final PersonalDataDto personalData) {
            this.personalData = personalData;
            return this;
        }

        public Builder setLogin(final String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public PersonDto build() {
            return new PersonDto(this);
        }
    }

}