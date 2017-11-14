package gm.vk.core.dto.group;

import java.util.Set;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.SubjectDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) public class GroupDto extends ResourceSupport {

  @JsonProperty("GroupId") private Integer id;

  @NotNull(message = "Invalid name of group") private String name;

  @NotNull(message = "Invalid course of group") private CourseDto course;

  @NotNull(message = "Invalid semester of group") private SemesterDto semester;

  @NotNull(message = "Invalid faculty of group") private FacultyDto faculty;

  private Set<PersonDto> persons;
  private Set<SubjectDto> subjects;

  public GroupDto() {
  }

  private GroupDto(final Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.course = builder.course;
    this.semester = builder.semester;
    this.faculty = builder.faculty;
    this.persons = builder.persons;
    this.subjects = builder.subjects;
  }

  public Integer getGroupId() {
    return id;
  }

  public void setGroupId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CourseDto getCourse() {
    return course;
  }

  public void setCourse(CourseDto course) {
    this.course = course;
  }

  public SemesterDto getSemester() {
    return semester;
  }

  public void setSemester(SemesterDto semester) {
    this.semester = semester;
  }

  public FacultyDto getFaculty() {
    return faculty;
  }

  public void setFaculty(FacultyDto faculty) {
    this.faculty = faculty;
  }

  public Set<PersonDto> getPersons() {
    return persons;
  }

  public void setPersons(Set<PersonDto> persons) {
    this.persons = persons;
  }

  public Set<SubjectDto> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<SubjectDto> subjects) {
    this.subjects = subjects;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof GroupDto))
      return false;

    GroupDto group = (GroupDto)o;

    return new EqualsBuilder().append(id, group.id).append(name, group.name).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(name).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("name", name).toString();
  }

  public static class Builder {
    private Integer id;
    private String name;
    private CourseDto course;
    private SemesterDto semester;
    private FacultyDto faculty;
    private Set<PersonDto> persons;
    private Set<SubjectDto> subjects;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setCourse(CourseDto course) {
      this.course = course;
      return this;
    }

    public Builder setSemester(SemesterDto semester) {
      this.semester = semester;
      return this;
    }

    public Builder setFaculty(FacultyDto faculty) {
      this.faculty = faculty;
      return this;
    }

    public Builder setPersons(Set<PersonDto> persons) {
      this.persons = persons;
      return this;
    }

    public Builder setSubjects(Set<SubjectDto> subjects) {
      this.subjects = subjects;
      return this;
    }

    public GroupDto build() {
      return new GroupDto(this);
    }
  }
}
