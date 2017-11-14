package gm.vk.core.dto.group.course;

import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.GroupDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) public class CourseDto extends ResourceSupport {

  @JsonProperty("CourseId") private Integer id;

  @NotNull @Min(value = 1, message = "Course should be in 1 to 6 range") @Max(value = 6, message = "Course should be in 1 to 6 range") private Integer course;

  private Set<GroupDto> groups;

  public CourseDto() {
  }

  public CourseDto(Integer id, Integer course) {
    this.id = id;
    this.course = course;
  }

  public CourseDto(Integer id, Integer course, Set<GroupDto> groups) {
    this.id = id;
    this.course = course;
    this.groups = groups;
  }

  public Integer getCourseId() {
    return id;
  }

  public void setCourseId(Integer id) {
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

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof CourseDto))
      return false;

    CourseDto c = (CourseDto)o;

    return new EqualsBuilder().append(id, c.id).append(course, c.course).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(course).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("course", course).toString();
  }
}
