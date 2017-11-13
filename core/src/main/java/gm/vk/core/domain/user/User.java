package gm.vk.core.domain.user;

import gm.vk.core.domain.person.Person;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "groupmanagement")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", unique = true)
  private Person person;

  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  public User() {}

  public User(Integer id, Person person, String login, String password) {
    this.id = id;
    this.person = person;
    this.login = login;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof User)) return false;

    User user = (User) o;

    return new EqualsBuilder()
        .append(id, user.id)
        .append(login, user.login)
        .append(password, user.password)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(login).append(password).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("login", login)
        .append("password", password)
        .toString();
  }
}
