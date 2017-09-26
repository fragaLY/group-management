package gm.vk.core.converter.user;

import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("userDtoConverter")
public class UserDtoConverter implements Function<UserDto, User> {

    @Autowired
    private PersonDtoConverter personConverter;

    @Override
    public User apply(@NotNull final UserDto userDto) {
        return new User(userDto.getId(), personConverter.apply(userDto.getPerson()), userDto.getLogin(), userDto.getPassword());
    }
}