package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("gradeDtoConverter")
public class GradeDtoConverter implements Function<GradeDto, Grade> {

  @Override
  public Grade apply(@NotNull final GradeDto gradeDto) {
    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();
    return new Grade(
        gradeDto.getId(),
        gradeDto.getGrade(),
        gradeDto
            .getExaminations()
            .stream()
            .map(customExaminationConverter)
            .collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<ExaminationDto, Examination> {

    @Override
    public Examination apply(ExaminationDto examination) {
      return new Examination(
          examination.getId(),
          new ExaminationType(examination.getType().getId(), examination.getType().getType()),
          new Grade(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }
}
