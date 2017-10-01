package gm.vk.core.dto.person.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;
import gm.vk.core.domain.person.role.Role;
import gm.vk.core.dto.person.PersonDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class PersonRoleDto {

    public PersonRoleDto() {
    }

    public PersonRoleDto(final Integer id, final Role role, final Set<PersonDto> persons) {
        this.id = id;
        this.role = role;
        this.persons = persons;
    }

    @JsonProperty("PersonRoleId")
    private Integer id;

    @NotNull(message = "Invalid role")
    private Role role;

    @Nullable
    private Set<PersonDto> persons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<PersonDto> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonDto> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PersonRoleDto)) return false;

        PersonRoleDto that = (PersonRoleDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(role, that.role)
                .append(persons, that.persons)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(role)
                .append(persons)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("role", role)
                .append("persons", persons)
                .toString();
    }
}