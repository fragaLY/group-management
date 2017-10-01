package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeDtoConverter")
public class ExaminationTypeDtoConverter implements Function<ExaminationTypeDto, ExaminationType> {

    @Autowired
    private ExaminationDtoConverter examinationDtoConverter;

    @Override
    public ExaminationType apply(final ExaminationTypeDto examinationTypeDto) {
        return new ExaminationType(examinationTypeDto.getId(), examinationTypeDto.getType(), examinationTypeDto.getExaminations().stream().map(examinationDtoConverter).collect(Collectors.toSet()));
    }
}