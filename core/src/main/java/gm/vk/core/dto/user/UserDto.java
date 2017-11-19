package gm.vk.core.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import gm.vk.core.dto.person.PersonDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends ResourceSupport {

  private static final String LOGIN_REGEXP = "^[a-zA-Z][a-zA-Z0-9_.,-]{5,31}$";
    private static final String PASSWORD_REGEXP =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,50})";

  @JsonIgnore private Integer id;

    @NotNull(message = "The user should be an any person")
    private PersonDto person;

    @Pattern(regexp = LOGIN_REGEXP, message = "Invalid login")
    @Size(min = 3, max = 31)
    private String login;

    @JsonIgnore
    @Pattern(
            regexp = PASSWORD_REGEXP,
            message =
                    "Password should contains at least one upper case literal, one lower case literal, one symbol and number."
    )
    private String password;

    public UserDto() {
    }

  public UserDto(Integer id, PersonDto person, String login, String password) {
    this.id = id;
    this.person = person;
    this.login = login;
    this.password = password;
  }

  public Integer getUserId() {
    return id;
  }

  public void setUserId(Integer id) {
    this.id = id;
  }

  public PersonDto getPerson() {
    return person;
  }

  public void setPerson(PersonDto person) {
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

        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        return new EqualsBuilder()
                .append(id, userDto.id)
                .append(login, userDto.login)
                .append(password, userDto.password)
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
