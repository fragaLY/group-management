package gm.vk.core.converter.user;

import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("userConverter")
public class UserConverter implements Function<User, UserDto> {

    @Autowired
    private PersonConverter personConverter;

    @Override
    public UserDto apply(@NotNull final User user) {
        return new UserDto(user.getId(), personConverter.apply(user.getPerson()), user.getLogin(), user.getPassword());
    }

}