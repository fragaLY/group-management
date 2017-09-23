package gm.vk.core.dto.data.personal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonalDataDto {

    private static final String NAME_REGEXP = "[A-Za-z]+";

    public PersonalDataDto() {
    }

    private PersonalDataDto(final Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
    }

    @JsonProperty("PersonalDataId")
    private Integer id;

    @Pattern(regexp = NAME_REGEXP)
    @Size(min = 2, max = 100)
    private String firstName;

    @Pattern(regexp = NAME_REGEXP)
    @Size(min = 2, max = 100)
    private String secondName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PersonalDataDto)) return false;

        PersonalDataDto that = (PersonalDataDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(firstName, that.firstName)
                .append(secondName, that.secondName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstName)
                .append(secondName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("secondName", secondName)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private String firstName;
        private String secondName;

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(final String secondName) {
            this.secondName = secondName;
            return this;
        }

        public PersonalDataDto build() {
            return new PersonalDataDto(this);
        }

    }
}