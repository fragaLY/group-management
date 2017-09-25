package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("GradeConverter")
public class GradeConverter implements Function<Grade, GradeDto> {

    @Override
    public GradeDto apply(final Grade grade) {
        return new GradeDto(grade.getId(), grade.getGrade());
    }
}