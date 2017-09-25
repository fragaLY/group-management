package gm.vk.core.converter.subject.examination;

import gm.vk.core.converter.subject.examination.grade.GradeConverter;
import gm.vk.core.converter.subject.examination.type.ExaminationTypeConverter;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("examinationConverter")
public class ExaminationConverter implements Function<Examination, ExaminationDto> {

    @Autowired
    private ExaminationTypeConverter examinationTypeConverter;

    @Autowired
    private GradeConverter gradeConverter;

    @Override
    public ExaminationDto apply(final Examination examination) {
        return new ExaminationDto(examination.getId(), examinationTypeConverter.apply(examination.getType()), gradeConverter.apply(examination.getGrade()));
    }
}