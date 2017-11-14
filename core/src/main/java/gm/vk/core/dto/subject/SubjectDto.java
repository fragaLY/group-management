package gm.vk.core.dto.subject;

import java.util.Set;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) public class SubjectDto extends ResourceSupport {

  @JsonProperty("SubjectId") private Integer id;

  @NotNull(message = "The name of examination could not be empty") private String name;

  @NotNull(message = "The examination could not be empty") private ExaminationDto examination;

  private Set<PersonDto> persons;
  private Set<GroupDto> groups;

  public SubjectDto() {
  }

  public SubjectDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public SubjectDto(
      final Integer id,
      final String name,
      final ExaminationDto examination,
      final Set<PersonDto> persons,
      final Set<GroupDto> groups) {
    this.id = id;
    this.name = name;
    this.examination = examination;
    this.persons = persons;
    this.groups = groups;
  }

  public Integer getSubjectId() {
    return id;
  }

  public void setSubjectId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ExaminationDto getExamination() {
    return examination;
  }

  public void setExamination(ExaminationDto examination) {
    this.examination = examination;
  }

  public Set<PersonDto> getPersons() {
    return persons;
  }

  public void setPersons(Set<PersonDto> persons) {
    this.persons = persons;
  }

  public Set<GroupDto> getGroups() {
    return groups;
  }

  public void setGroups(Set<GroupDto> groups) {
    this.groups = groups;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof SubjectDto))
      return false;

    SubjectDto that = (SubjectDto)o;

    return new EqualsBuilder().append(id, that.id).append(name, that.name).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(name).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("name", name).toString();
  }
}
