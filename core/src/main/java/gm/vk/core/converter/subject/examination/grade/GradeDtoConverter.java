package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("gradeDtoConverter")
public class GradeDtoConverter implements Function<GradeDto, Grade> {

    @Override
    public Grade apply(@NotNull final GradeDto gradeDto) {
        return new Grade(gradeDto.getId(), gradeDto.getGrade());
    }
}