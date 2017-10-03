package gm.vk.core.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubjectDto {

  @JsonProperty("SubjectId")
  private Integer id;
  @NotNull(message = "The name of examination could not be empty")
  private String name;
  @NotNull(message = "The examination could not be empty")
  private ExaminationDto examination;
  private Set<PersonDto> persons;
  private Set<GroupDto> groups;

  public SubjectDto() {}

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof SubjectDto)) return false;

    SubjectDto that = (SubjectDto) o;

    return new EqualsBuilder()
        .append(id, that.id)
        .append(name, that.name)
        .append(examination, that.examination)
        .append(persons, that.persons)
        .append(groups, that.groups)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(name)
        .append(examination)
        .append(persons)
        .append(groups)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("name", name)
        .append("examination", examination)
        .append("persons", persons)
        .append("groups", groups)
        .toString();
  }
}
