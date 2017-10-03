package gm.vk.core.converter.user;

import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.user.UserDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("userDtoConverter")
public class UserDtoConverter implements Function<UserDto, User> {

  @Override
  public User apply(@NotNull final UserDto userDto) {
    return new User(
        userDto.getId(),
        new CustomPersonConverter().apply(userDto.getPerson()),
        userDto.getLogin(),
        userDto.getPassword());
  }

  private class CustomPersonConverter implements Function<PersonDto, Person> {

    @Override
    public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getId()).build();
    }
  }
}
