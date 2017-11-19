package gm.vk.core.domain.person.role;

import gm.vk.core.domain.person.Person;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personRole", schema = "groupmanagement")
public class PersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", unique = true, nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<Person> persons;

    public PersonRole() {
    }

  public PersonRole(Integer id, Role role) {
    this.id = id;
    this.role = role;
  }

  public PersonRole(final Integer id, final Role role, final Set<Person> persons) {
    this.id = id;
    this.role = role;
    this.persons = persons;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Set<Person> getPersons() {
    return persons;
  }

  public void setPersons(Set<Person> persons) {
    this.persons = persons;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PersonRole)) return false;

        PersonRole that = (PersonRole) o;

    return new EqualsBuilder().append(id, that.id).append(role, that.role).isEquals();
  }

    @Override
    public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(role).toHashCode();
  }

    @Override
    public String toString() {
    return new ToStringBuilder(this).append("id", id).append("role", role).toString();
  }
}
