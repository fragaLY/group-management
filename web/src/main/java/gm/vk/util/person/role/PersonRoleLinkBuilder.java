package gm.vk.util.person.role;

import gm.vk.controllers.person.role.PersonRoleController;
import gm.vk.core.dto.person.role.PersonRoleDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonRoleLinkBuilder implements Consumer<PersonRoleDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param personRole the input argument
     */
    @Override
    public void accept(@NotNull final PersonRoleDto personRole) {

        personRole.add(
                linkTo(methodOn(PersonRoleController.class).getPersonRoles())
                        .slash(personRole.getPersonRoleId())
                        .withSelfRel());
    }
}
