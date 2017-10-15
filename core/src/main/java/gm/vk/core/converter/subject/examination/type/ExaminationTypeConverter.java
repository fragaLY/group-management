package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeConverter")
public class ExaminationTypeConverter implements Function<ExaminationType, ExaminationTypeDto> {

  @Override
  public ExaminationTypeDto apply(final ExaminationType examinationType) {
    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();

    return new ExaminationTypeDto(
        examinationType.getId(),
        examinationType.getType(),
        examinationType
            .getExaminations()
            .stream()
            .map(customExaminationConverter)
            .collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<Examination, ExaminationDto> {

    @Override
    public ExaminationDto apply(Examination examination) {
        final Grade grade = examination.getGrade();

        Integer id = null;
        Integer gradeValue = null;

        if (grade != null) {
            id = grade.getId();
            gradeValue = grade.getGrade();
        }

        final ExaminationType type = examination.getType();

      return new ExaminationDto(
          examination.getId(),
              new ExaminationTypeDto(type.getId(), type.getType()),
              new GradeDto(id, gradeValue));
    }
  }
}
