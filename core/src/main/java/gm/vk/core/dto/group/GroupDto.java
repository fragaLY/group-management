package gm.vk.core.dto.group;


import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.core.dto.group.faculty.FacultyDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.SubjectDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class GroupDto {

    public GroupDto() {
    }

    //todo vk: replace it with Builder
    public GroupDto(Integer id, String name, CourseDto course, SemesterDto semester, FacultyDto faculty, Set<PersonDto> persons, Set<SubjectDto> subjects) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.faculty = faculty;
        this.persons = persons;
        this.subjects = subjects;
    }

    @JsonProperty("GroupId")
    private Integer id;

    @NotNull(message = "Invalid name of group")
    private String name;

    @NotNull(message = "Invalid course of group")
    private CourseDto course;

    @NotNull(message = "Invalid semester of group")
    private SemesterDto semester;

    @NotNull(message = "Invalid faculty of group")
    private FacultyDto faculty;

    private Set<PersonDto> persons;

    private Set<SubjectDto> subjects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GroupDto)) return false;

        GroupDto group = (GroupDto) o;

        return new EqualsBuilder()
                .append(id, group.id)
                .append(name, group.name)
                .append(course, group.course)
                .append(semester, group.semester)
                .append(faculty, group.faculty)
                .append(persons, group.persons)
                .append(subjects, group.subjects)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(course)
                .append(semester)
                .append(faculty)
                .append(persons)
                .append(subjects)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("course", course)
                .append("semester", semester)
                .append("faculty", faculty)
                .append("persons", persons)
                .append("subjects", subjects)
                .toString();
    }
}