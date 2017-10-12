package gm.vk.core.domain.subject;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.subject.examination.Examination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject", schema = "groupmanagement")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  @Column(name = "name", unique = true, nullable = false)
  private String name;
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "examination_id", nullable = false)
  private Examination examination;
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
  private Set<Person> persons;
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
  private Set<Group> groups;

  public Subject() {}

  public Subject(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Subject(
      final Integer id,
      final String name,
      final Examination examination,
      final Set<Person> persons,
      final Set<Group> groups) {
    this.id = id;
    this.name = name;
    this.examination = examination;
    this.persons = persons;
    this.groups = groups;
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

  public Examination getExamination() {
    return examination;
  }

  public void setExamination(Examination examination) {
    this.examination = examination;
  }

  public Set<Person> getPersons() {
    return persons;
  }

  public void setPersons(Set<Person> persons) {
    this.persons = persons;
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

    if (!(o instanceof Subject)) return false;

    Subject subject = (Subject) o;

    return new EqualsBuilder()
        .append(id, subject.id)
        .append(name, subject.name)
        .append(examination, subject.examination)
        .append(persons, subject.persons)
        .append(groups, subject.groups)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(name)
        .append(examination)
        .append(persons)
        .append(groups)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("name", name)
        .append("examination", examination)
        .append("persons", persons)
        .append("groups", groups)
        .toString();
  }
}
