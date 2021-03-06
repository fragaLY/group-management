package gm.vk.core.dto.group.faculty;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.GroupDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class FacultyDto {

  @JsonProperty("FacultyId")
  private Integer id;
  @NotNull(message = "Invalid faculty")
  private String faculty;
  private Set<GroupDto> groups;

  public FacultyDto() {}

  public FacultyDto(Integer id, String faculty) {
    this.id = id;
    this.faculty = faculty;
  }

  public FacultyDto(Integer id, String faculty, Set<GroupDto> groups) {
    this.id = id;
    this.faculty = faculty;
    this.groups = groups;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
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

    if (!(o instanceof FacultyDto)) return false;

    FacultyDto faculty1 = (FacultyDto) o;

    return new EqualsBuilder()
        .append(id, faculty1.id)
        .append(faculty, faculty1.faculty)
        .append(groups, faculty1.groups)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(faculty).append(groups).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("faculty", faculty)
        .append("groups", groups)
        .toString();
  }
}
