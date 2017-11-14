package gm.vk.core.dto.person.role;

import java.util.Set;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.domain.person.role.Role;
import gm.vk.core.dto.person.PersonDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonIgnoreProperties(ignoreUnknown = true) public class PersonRoleDto
    extends ResourceSupport {

  @JsonProperty("PersonRoleId") private Integer id;

  @NotNull(message = "Invalid role") private Role role;

  private Set<PersonDto> persons;

  public PersonRoleDto() {
  }

  public PersonRoleDto(Integer id, Role role) {
    this.id = id;
    this.role = role;
  }

  public PersonRoleDto(final Integer id, final Role role, final Set<PersonDto> persons) {
    this.id = id;
    this.role = role;
    this.persons = persons;
  }

  public Integer getPersonRoleId() {
    return id;
  }

  public void setPersonRoleId(Integer id) {
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

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof PersonRoleDto))
      return false;

    PersonRoleDto that = (PersonRoleDto)o;

    return new EqualsBuilder().append(id, that.id).append(role, that.role).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(role).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("role", role).toString();
  }
}
