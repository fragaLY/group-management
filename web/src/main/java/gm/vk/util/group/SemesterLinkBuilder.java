package gm.vk.util.group;

import gm.vk.controllers.group.SemesterController;
import gm.vk.core.dto.group.SemesterDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SemesterLinkBuilder implements Consumer<SemesterDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param semester the input argument
     */
    @Override
    public void accept(@NotNull final SemesterDto semester) {

        semester.add(
                linkTo(methodOn(SemesterController.class).getSemesters())
                        .slash(semester.getSemesterId())
                        .withSelfRel());
    }
}
