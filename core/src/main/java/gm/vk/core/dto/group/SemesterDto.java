package gm.vk.core.dto.group;

import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SemesterDto {

  @JsonProperty("SemesterId")
  private Integer id;

  @NotNull
  @Min(value = 1, message = "Semester should be in 1 to 12 range")
  @Max(value = 12, message = "Semester should be in 1 to 12 range")
  private Integer semester;

  private Set<GroupDto> groups;

  public SemesterDto() {}

  public SemesterDto(Integer id, Integer semester) {
    this.id = id;
    this.semester = semester;
  }

  public SemesterDto(Integer id, Integer semester, Set<GroupDto> groups) {
    this.id = id;
    this.semester = semester;
    this.groups = groups;
  }

  public Integer getSemesterId() {
    return id;
  }

  public void setSemesterId(Integer id) {
    this.id = id;
  }

  public Integer getSemester() {
    return semester;
  }

  public void setSemester(Integer semester) {
    this.semester = semester;
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

    if (!(o instanceof SemesterDto)) return false;

    SemesterDto semester1 = (SemesterDto) o;

    return new EqualsBuilder()
        .append(id, semester1.id)
        .append(semester, semester1.semester)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(semester).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("semester", semester)
        .toString();
  }
}
