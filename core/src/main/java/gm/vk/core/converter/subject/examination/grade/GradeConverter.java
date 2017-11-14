package gm.vk.core.converter.subject.examination.grade;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("GradeConverter") public class GradeConverter implements Function<Grade, GradeDto> {

  private static final Logger LOG = LoggerFactory.getLogger(GradeConverter.class);

  /**
   * Converts {@link Grade} to {@link GradeDto}
   *
   * @param grade - the {@link Grade}
   * @return {@link GradeDto}
   */
  @Override public GradeDto apply(@NotNull final Grade grade) {

    LOG.info("Converts Grade [{}] to GradeDto", grade);

    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();

    return new GradeDto(
        grade.getId(),
        grade.getGrade(),
        grade.getExaminations().stream().map(customExaminationConverter).collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<Examination, ExaminationDto> {

    @Override public ExaminationDto apply(Examination examination) {
      return new ExaminationDto(
          examination.getId(),
          new ExaminationTypeDto(examination.getType().getId(), examination.getType().getType()),
          new GradeDto(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }
}
