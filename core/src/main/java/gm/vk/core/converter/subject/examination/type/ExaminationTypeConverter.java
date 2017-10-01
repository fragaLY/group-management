package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeConverter")
public class ExaminationTypeConverter implements Function<ExaminationType, ExaminationTypeDto>{

    @Autowired
    private ExaminationConverter examinationConverter;

    @Override
    public ExaminationTypeDto apply(final ExaminationType examinationType) {
        return new ExaminationTypeDto(examinationType.getId(), examinationType.getType(), examinationType.getExaminations().stream().map(examinationConverter).collect(Collectors.toSet()));
    }
}