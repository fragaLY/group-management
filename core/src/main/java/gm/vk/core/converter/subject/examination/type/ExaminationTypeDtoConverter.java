package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("examinationTypeDtoConverter")
public class ExaminationTypeDtoConverter implements Function<ExaminationTypeDto, ExaminationType> {

    @Override
    public ExaminationType apply(final ExaminationTypeDto examinationTypeDto) {
        return new ExaminationType(examinationTypeDto.getId(), examinationTypeDto.getType());
    }
}