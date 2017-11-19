package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeConverter")
public class ExaminationTypeConverter implements Function<ExaminationType, ExaminationTypeDto> {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationTypeConverter.class);

  /**
   * Converts {@link ExaminationType} to {@link ExaminationTypeDto}
   *
   * @param examinationType - the {@link ExaminationType}
   * @return {@link ExaminationTypeDto}
   */
  @Override
  public ExaminationTypeDto apply(@NotNull final ExaminationType examinationType) {

    LOG.info("Converts ExaminationType [{}] to ExaminationTypeDto", examinationType);

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
