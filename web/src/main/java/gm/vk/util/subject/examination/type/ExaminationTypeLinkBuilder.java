package gm.vk.util.subject.examination.type;

import gm.vk.controllers.subject.examination.type.ExaminationTypeController;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ExaminationTypeLinkBuilder implements Consumer<ExaminationTypeDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param examinationType the input argument
     */
    @Override
    public void accept(@NotNull final ExaminationTypeDto examinationType) {

        examinationType.add(
                linkTo(methodOn(ExaminationTypeController.class).getExaminationTypes())
                        .slash(examinationType.getExaminationTypeId())
                        .withSelfRel());
    }
}
