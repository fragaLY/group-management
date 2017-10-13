package gm.vk.core.domain.person;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.person.role.PersonRole;
import gm.vk.core.domain.subject.Subject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person", schema = "groupmanagement")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "personrole_id", referencedColumnName = "id")
  private PersonRole role;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "contacts_id", referencedColumnName = "id")
  private Contacts contacts;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "personaldata_id")
  private PersonalData personalData;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
    schema = "groupmanagement",
    name = "personid_subjectid",
    joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "subject_id", nullable = false, updatable = false)}
  )
  private Set<Subject> subjects;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
    schema = "groupmanagement",
    name = "personid_groupid",
    joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)}
  )
  private Group group;

  public Person() {}

  private Person(final Builder builder) {
    this.id = builder.id;
    this.role = builder.role;
    this.contacts = builder.contacts;
    this.personalData = builder.personalData;
    this.subjects = builder.subjects;
    this.group = builder.group;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PersonRole getRole() {
    return role;
  }

  public void setRole(PersonRole role) {
    this.role = role;
  }

  public Contacts getContacts() {
    return contacts;
  }

  public void setContacts(Contacts contacts) {
    this.contacts = contacts;
  }

  public PersonalData getPersonalData() {
    return personalData;
  }

  public void setPersonalData(PersonalData personalData) {
    this.personalData = personalData;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public static class Builder {

    private Integer id;
    private PersonRole role;
    private Contacts contacts;
    private PersonalData personalData;
    private Set<Subject> subjects;
    private Group group;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setRole(PersonRole role) {
      this.role = role;
      return this;
    }

    public Builder setContacts(Contacts contacts) {
      this.contacts = contacts;
      return this;
    }

    public Builder setPersonalData(PersonalData personalData) {
      this.personalData = personalData;
      return this;
    }

    public Builder setSubjects(Set<Subject> subjects) {
      this.subjects = subjects;
      return this;
    }

    public Builder setGroup(Group group) {
      this.group = group;
      return this;
    }

    public Person build() {
      return new Person(this);
    }
  }
}
