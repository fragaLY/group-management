package gm.vk.util.subject;

import gm.vk.controllers.subject.SubjectController;
import gm.vk.controllers.subject.examination.ExaminationController;
import gm.vk.core.dto.subject.SubjectDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SubjectLinkBuilder implements Consumer<SubjectDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param subject the input argument
     */
    @Override
    public void accept(@NotNull final SubjectDto subject) {

        subject.add(
                linkTo(methodOn(SubjectController.class).getSubjects())
                        .slash(subject.getSubjectId())
                        .withSelfRel());

        subject.add(
                linkTo(methodOn(ExaminationController.class).getExaminations())
                        .slash(subject.getExamination().getExaminationId())
                        .withRel("examination"));
    }
}
