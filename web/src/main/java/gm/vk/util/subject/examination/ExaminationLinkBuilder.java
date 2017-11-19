package gm.vk.util.subject.examination;

import gm.vk.controllers.subject.examination.ExaminationController;
import gm.vk.controllers.subject.examination.grade.GradeController;
import gm.vk.controllers.subject.examination.type.ExaminationTypeController;
import gm.vk.core.dto.subject.examination.ExaminationDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExaminationLinkBuilder implements Consumer<ExaminationDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param examination the input argument
     */
    @Override
    public void accept(@NotNull final ExaminationDto examination) {

        examination.add(
                linkTo(methodOn(ExaminationController.class).getExaminations())
                        .slash(examination.getExaminationId())
                        .withSelfRel());

        examination.add(
                linkTo(methodOn(ExaminationTypeController.class).getExaminationTypes())
                        .slash(examination.getType().getExaminationTypeId())
                        .withRel("examinationtype"));

        examination.add(
                linkTo(methodOn(GradeController.class).getGrades())
                        .slash(examination.getGrade().getGradeId())
                        .withRel("grade"));
    }
}
