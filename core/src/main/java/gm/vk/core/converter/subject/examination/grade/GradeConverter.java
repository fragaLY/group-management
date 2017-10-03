package gm.vk.core.converter.subject.examination.grade;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("GradeConverter")
public class GradeConverter implements Function<Grade, GradeDto> {

  @Override
  public GradeDto apply(@NotNull final Grade grade) {
    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();

    return new GradeDto(
        grade.getId(),
        grade.getGrade(),
        grade
            .getExaminations()
            .stream()
            .map(customExaminationConverter)
            .collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<Examination, ExaminationDto> {

    @Override
    public ExaminationDto apply(Examination examination) {
      return new ExaminationDto(
          examination.getId(),
          new ExaminationTypeDto(examination.getType().getId(), examination.getType().getType()),
          new GradeDto(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }
}
