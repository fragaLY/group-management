package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("gradeDtoConverter")
public class GradeDtoConverter implements Function<GradeDto, Grade> {

    @Autowired
    private ExaminationDtoConverter examinationDtoConverter;

    @Override
    public Grade apply(@NotNull final GradeDto gradeDto) {
        return new Grade(gradeDto.getId(), gradeDto.getGrade(), gradeDto.getExaminations().stream().map(examinationDtoConverter).collect(Collectors.toSet()));
    }
}