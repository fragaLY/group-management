package gm.vk.core.domain.group.faculty;

import gm.vk.core.domain.group.Group;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Faculty", schema = "groupmanagement")
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "faculty")
  private String faculty;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
  private Set<Group> groups;

  public Faculty() {}

  public Faculty(Integer id, String faculty) {
    this.id = id;
    this.faculty = faculty;
  }

  public Faculty(Integer id, String faculty, Set<Group> groups) {
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

  public Set<Group> getGroups() {
    return groups;
  }

  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Faculty)) return false;

    Faculty faculty1 = (Faculty) o;

      return new EqualsBuilder().append(id, faculty1.id).append(faculty, faculty1.faculty).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(faculty).append(groups).toHashCode();
  }

  @Override
  public String toString() {
      return new ToStringBuilder(this).append("id", id).append("faculty", faculty).toString();
  }
}
