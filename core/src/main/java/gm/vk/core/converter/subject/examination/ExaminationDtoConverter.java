package gm.vk.core.converter.subject.examination;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("examinationDtoConverter") public class ExaminationDtoConverter
    implements Function<ExaminationDto, Examination> {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationDtoConverter.class);

  /**
   * Converts {@link ExaminationDto} to {@link Examination}
   *
   * @param examinationDto - the {@link ExaminationDto}
   * @return {@link Examination}
   */
  @Override public Examination apply(@NotNull final ExaminationDto examinationDto) {

    LOG.info("Converts ExaminationDto [{}] to Examination", examinationDto);

    return new Examination(
        examinationDto.getExaminationId(),
        new ExaminationType(examinationDto.getType().getExaminationTypeId(),
            examinationDto.getType().getType()),
        new Grade(examinationDto.getGrade().getGradeId(), examinationDto.getGrade().getGrade()));
  }
}
