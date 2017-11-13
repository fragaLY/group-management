package gm.vk.core.converter.subject.examination;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("examinationConverter")
public class ExaminationConverter implements Function<Examination, ExaminationDto> {

  @Override
  public ExaminationDto apply(@NotNull final Examination examination) {

      final ExaminationType type = examination.getType();
      final Grade grade = examination.getGrade();

      Integer gradeId = null;
      Integer gradeValue = null;

      if (grade != null) {
          gradeId = grade.getId();
          gradeValue = grade.getGrade();
      }

    return new ExaminationDto(
        examination.getId(),
            new ExaminationTypeDto(type.getId(), type.getType()),
            new GradeDto(gradeId, gradeValue));
  }
}
