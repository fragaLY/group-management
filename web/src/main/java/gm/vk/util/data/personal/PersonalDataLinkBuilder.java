package gm.vk.util.data.personal;

import gm.vk.controllers.data.personal.PersonalDataController;
import gm.vk.core.dto.data.personal.PersonalDataDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonalDataLinkBuilder implements Consumer<PersonalDataDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param personalData the input argument
     */
    @Override
    public void accept(@NotNull final PersonalDataDto personalData) {

        personalData.add(
                linkTo(methodOn(PersonalDataController.class).getAllPersonalData())
                        .slash(personalData.getPersonalDataId())
                        .withSelfRel());
    }
}