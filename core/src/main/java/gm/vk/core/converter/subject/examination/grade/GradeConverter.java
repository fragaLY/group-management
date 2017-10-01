package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("GradeConverter")
public class GradeConverter implements Function<Grade, GradeDto> {

    @Autowired
    private ExaminationConverter examinationConverter;

    @Override
    public GradeDto apply(@NotNull final Grade grade) {
        return new GradeDto(grade.getId(), grade.getGrade(), grade.getExaminations().stream().map(examinationConverter).collect(Collectors.toSet()));
    }
}