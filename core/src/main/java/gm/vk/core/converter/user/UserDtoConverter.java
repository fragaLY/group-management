package gm.vk.core.converter.user;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("userDtoConverter") public class UserDtoConverter implements Function<UserDto, User> {

  private static final Logger LOG = LoggerFactory.getLogger(UserDtoConverter.class);

  /**
   * Converts {@link UserDto} to {@link User}
   *
   * @param userDto - the {@link UserDto}
   * @return {@link User}
   */
  @Override public User apply(@NotNull final UserDto userDto) {

    LOG.info("Converts UserDto [{}] to User", userDto);

    return new User(
        userDto.getUserId(),
        new CustomPersonConverter().apply(userDto.getPerson()),
        userDto.getLogin(),
        userDto.getPassword());
  }

  private class CustomPersonConverter implements Function<PersonDto, Person> {

    @Override public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getPersonId()).build();
    }
  }
}
