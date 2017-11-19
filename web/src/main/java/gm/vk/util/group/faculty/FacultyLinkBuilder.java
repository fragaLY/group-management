package gm.vk.util.group.faculty;

import gm.vk.controllers.group.faculty.FacultyController;
import gm.vk.core.dto.group.faculty.FacultyDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class FacultyLinkBuilder implements Consumer<FacultyDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param faculty the input argument
     */
    @Override
    public void accept(@NotNull final FacultyDto faculty) {

        faculty.add(
                linkTo(methodOn(FacultyController.class).getFaculties())
                        .slash(faculty.getFacultyId())
                        .withSelfRel());
    }
}
