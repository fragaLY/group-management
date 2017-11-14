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

@Component("userConverter") public class UserConverter implements Function<User, UserDto> {

  private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);

  /**
   * Converts {@link User} to {@link UserDto}
   *
   * @param user - the {@link User}
   * @return {@link UserDto}
   */
  @Override public UserDto apply(@NotNull final User user) {

    LOG.info("Converts User [{}] to UserDto", user);

    return new UserDto(
        user.getId(),
        new CustomPersonConverter().apply(user.getPerson()),
        user.getLogin(),
        user.getPassword());
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }
}
