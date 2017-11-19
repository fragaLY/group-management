package gm.vk.core.domain.data.personal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "personalData", schema = "groupmanagement")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "secondName", nullable = false)
    private String secondName;

    public PersonalData() {
    }

  public PersonalData(final Integer id, final String firstName, final String secondName) {
    this.id = id;
    this.firstName = firstName;
    this.secondName = secondName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PersonalData)) return false;

        PersonalData that = (PersonalData) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(firstName, that.firstName)
                .append(secondName, that.secondName)
                .isEquals();
  }

    @Override
    public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(firstName).append(secondName).toHashCode();
  }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("secondName", secondName)
                .toString();
  }
}
