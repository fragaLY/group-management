package gm.vk.core.dto.data.personal;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) public class PersonalDataDto extends ResourceSupport {

  @JsonProperty("PersonalDataId") private Integer id;

  @Size(max = 100, message = "The firstname could not be greater than 100 literals") private String firstName;

  @Size(max = 100, message = "The secondname could not be greater than 100 literals") private String secondName;

  public PersonalDataDto() {
  }

  public PersonalDataDto(final Integer id, final String firstName, final String secondName) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
  }

  public Integer getPersonalDataId() {
    return id;
  }

  public void setPersonalDataId(Integer id) {
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

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof PersonalDataDto))
      return false;

    PersonalDataDto that = (PersonalDataDto)o;

    return new EqualsBuilder().append(id, that.id).append(firstName, that.firstName).append(secondName,
        that.secondName).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(firstName).append(secondName).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("firstName", firstName).append("secondName",
        secondName).toString();
  }
}
