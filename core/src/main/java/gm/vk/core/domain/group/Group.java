package gm.vk.core.domain.group;

import gm.vk.core.domain.group.course.Course;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.subject.Subject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "group", schema = "groupmanagement")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "semester_id", referencedColumnName = "id")
  private Semester semester;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id", referencedColumnName = "id")
  private Faculty faculty;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
  private Set<Person> persons;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
    schema = "groupmanagement",
    name = "subjectid_groupid",
    joinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "subject_id", nullable = false, updatable = false)}
  )
  private Set<Subject> subjects;

  public Group() {}

  private Group(final Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.course = builder.course;
    this.semester = builder.semester;
    this.faculty = builder.faculty;
    this.persons = builder.persons;
    this.subjects = builder.subjects;
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

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Semester getSemester() {
    return semester;
  }

  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Set<Person> getPersons() {
    return persons;
  }

  public void setPersons(Set<Person> persons) {
    this.persons = persons;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Group)) return false;

    Group group = (Group) o;

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

  public static class Builder {
    private Integer id;
    private String name;
    private Course course;
    private Semester semester;
    private Faculty faculty;
    private Set<Person> persons;
    private Set<Subject> subjects;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setCourse(Course course) {
      this.course = course;
      return this;
    }

    public Builder setSemester(Semester semester) {
      this.semester = semester;
      return this;
    }

    public Builder setFaculty(Faculty faculty) {
      this.faculty = faculty;
      return this;
    }

    public Builder setPersons(Set<Person> persons) {
      this.persons = persons;
      return this;
    }

    public Builder setSubjects(Set<Subject> subjects) {
      this.subjects = subjects;
      return this;
    }

    public Group build() {
      return new Group(this);
    }
  }
}
