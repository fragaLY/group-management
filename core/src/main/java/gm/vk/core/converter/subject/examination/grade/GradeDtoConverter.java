package gm.vk.core.converter.subject.examination.grade;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("gradeDtoConverter") public class GradeDtoConverter implements Function<GradeDto, Grade> {

  private static final Logger LOG = LoggerFactory.getLogger(GradeDtoConverter.class);

  /**
   * Converts {@link GradeDto} to {@link Grade}
   *
   * @param gradeDto - the {@link GradeDto}
   * @return {@link Grade}
   */
  @Override public Grade apply(@NotNull final GradeDto gradeDto) {

    LOG.info("Converts GradeDto [{}] to Grade", gradeDto);

    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();
    return new Grade(
        gradeDto.getGradeId(),
        gradeDto.getGrade(),
        gradeDto.getExaminations().stream().map(customExaminationConverter).collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<ExaminationDto, Examination> {

    @Override public Examination apply(ExaminationDto examination) {
      return new Examination(
          examination.getExaminationId(),
          new ExaminationType(examination.getType().getExaminationTypeId(), examination.getType().getType()),
          new Grade(examination.getGrade().getGradeId(), examination.getGrade().getGrade()));
    }
  }
}
