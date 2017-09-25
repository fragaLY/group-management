package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("examinationTypeConverter")
public class ExaminationTypeConverter implements Function<ExaminationType, ExaminationTypeDto>{

    @Override
    public ExaminationTypeDto apply(final ExaminationType examinationType) {
        return new ExaminationTypeDto(examinationType.getId(), examinationType.getType());
    }
}