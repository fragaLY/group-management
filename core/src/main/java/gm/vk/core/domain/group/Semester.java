package gm.vk.core.domain.group;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "semester", schema = "groupmanagement")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "semester")
    private Integer semester;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
    private Set<Group> groups;

    public Semester() {
    }

  public Semester(Integer id, Integer semester) {
    this.id = id;
    this.semester = semester;
  }

  public Semester(Integer id, Integer semester, Set<Group> groups) {
    this.id = id;
    this.semester = semester;
    this.groups = groups;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSemester() {
    return semester;
  }

  public void setSemester(Integer semester) {
    this.semester = semester;
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

        if (!(o instanceof Semester)) return false;

        Semester semester1 = (Semester) o;

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
    return new ToStringBuilder(this).append("id", id).append("semester", semester).toString();
  }
}
