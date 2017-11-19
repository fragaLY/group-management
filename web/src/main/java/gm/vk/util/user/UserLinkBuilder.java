package gm.vk.util.user;

import gm.vk.controllers.person.PersonController;
import gm.vk.controllers.user.UserController;
import gm.vk.core.dto.user.UserDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserLinkBuilder implements Consumer<UserDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param user the input argument
     */
    @Override
    public void accept(@NotNull final UserDto user) {

        user.add(
                linkTo(methodOn(UserController.class).getUsers()).slash(user.getUserId()).withSelfRel());

        user.add(
                linkTo(methodOn(PersonController.class).getPersons())
                        .slash(user.getPerson().getPersonId())
                        .withRel("person"));
    }
}
