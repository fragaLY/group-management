package gm.vk.core.converter.user;

import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.user.UserDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("userConverter")
public class UserConverter implements Function<User, UserDto> {

  @Override
  public UserDto apply(@NotNull final User user) {
    return new UserDto(
        user.getId(),
        new CustomPersonConverter().apply(user.getPerson()),
        user.getLogin(),
        user.getPassword());
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override
    public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }
}
