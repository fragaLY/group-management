package gm.vk.core.converter.subject.examination;

import gm.vk.core.converter.subject.examination.grade.GradeDtoConverter;
import gm.vk.core.converter.subject.examination.type.ExaminationTypeDtoConverter;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("examinationDtoConverter")
public class ExaminationDtoConverter implements Function<ExaminationDto, Examination> {

    @Autowired
    private ExaminationTypeDtoConverter examinationTypeDtoConverter;

    @Autowired
    private GradeDtoConverter gradeDtoConverter;

    @Override
    public Examination apply(@NotNull final ExaminationDto examinationDto) {
        return new Examination(examinationDto.getId(), examinationTypeDtoConverter.apply(examinationDto.getType()), gradeDtoConverter.apply(examinationDto.getGrade()));
    }
}