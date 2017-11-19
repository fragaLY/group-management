package gm.vk.util.subject.examination.grade;

import gm.vk.controllers.subject.examination.grade.GradeController;
import gm.vk.core.dto.subject.examination.grade.GradeDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GradeLinkBuilder implements Consumer<GradeDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param grade the input argument
     */
    @Override
    public void accept(@NotNull final GradeDto grade) {

        grade.add(
                linkTo(methodOn(GradeController.class).getGrades())
                        .slash(grade.getGradeId())
                        .withSelfRel());
    }
}
