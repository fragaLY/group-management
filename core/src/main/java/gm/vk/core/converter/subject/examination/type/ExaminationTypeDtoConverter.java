package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeDtoConverter")
public class ExaminationTypeDtoConverter implements Function<ExaminationTypeDto, ExaminationType> {

  private static final Logger LOG = LoggerFactory.getLogger(ExaminationTypeDtoConverter.class);

  /**
   * Converts {@link ExaminationTypeDto} to {@link ExaminationType}
   *
   * @param examinationTypeDto - the {@link ExaminationTypeDto}
   * @return {@link ExaminationType}
   */
  @Override
  public ExaminationType apply(@NotNull final ExaminationTypeDto examinationTypeDto) {

    LOG.info("Converts ExaminationTypeDto [{}] to ExaminationType", examinationTypeDto);

    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();
    return new ExaminationType(
        examinationTypeDto.getExaminationTypeId(),
        examinationTypeDto.getType(),
            examinationTypeDto
                    .getExaminations()
                    .stream()
                    .map(customExaminationConverter)
                    .collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<ExaminationDto, Examination> {

      @Override
      public Examination apply(ExaminationDto examination) {
      return new Examination(
          examination.getExaminationId(),
              new ExaminationType(
                      examination.getType().getExaminationTypeId(), examination.getType().getType()),
          new Grade(examination.getGrade().getGradeId(), examination.getGrade().getGrade()));
    }
  }
}
