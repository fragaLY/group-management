package gm.vk.core.converter.subject.examination;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("examinationConverter") public class ExaminationConverter
    implements Function<Examination, ExaminationDto> {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationConverter.class);

  /**
   * Converts {@link Examination} to {@link ExaminationDto}
   *
   * @param examination - the {@link Examination}
   * @return {@link ExaminationDto}
   */
  @Override public ExaminationDto apply(@NotNull final Examination examination) {

    LOG.info("Converts Examination [{}] to ExaminationDto", examination);

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
