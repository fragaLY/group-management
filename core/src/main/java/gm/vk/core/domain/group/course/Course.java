package gm.vk.core.domain.group.course;

import gm.vk.core.domain.group.Group;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course", schema = "groupmanagement")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "course")
    private Integer course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Group> groups;

    public Course() {
    }

  public Course(Integer id, Integer course) {
    this.id = id;
    this.course = course;
  }

  public Course(Integer id, Integer course, Set<Group> groups) {
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

  public Set<Group> getGroups() {
    return groups;
  }

  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Course)) return false;

        Course course1 = (Course) o;

    return new EqualsBuilder().append(id, course1.id).append(course, course1.course).isEquals();
  }

    @Override
    public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(course).append(groups).toHashCode();
  }

    @Override
    public String toString() {
    return new ToStringBuilder(this).append("id", id).append("course", course).toString();
  }
}
