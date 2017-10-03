package gm.vk.core.dto.group.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.GroupDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class CourseDto {

  @JsonProperty("CourseId")
  private Integer id;
  @NotNull
  @Min(value = 1, message = "Course should be in 1 to 6 range")
  @Max(value = 6, message = "Course should be in 1 to 6 range")
  private Integer course;
  private Set<GroupDto> groups;

  public CourseDto() {}

  public CourseDto(Integer id, Integer course) {
    this.id = id;
    this.course = course;
  }

  public CourseDto(Integer id, Integer course, Set<GroupDto> groups) {
    this.id = id;
    this.course = course;
    this.groups = groups;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCourse() {
    return course;
  }

  public void setCourse(Integer course) {
    this.course = course;
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

    if (!(o instanceof CourseDto)) return false;

    CourseDto course1 = (CourseDto) o;

    return new EqualsBuilder()
        .append(id, course1.id)
        .append(course, course1.course)
        .append(groups, course1.groups)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(course).append(groups).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("course", course)
        .append("groups", groups)
        .toString();
  }
}
