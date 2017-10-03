package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("examinationTypeDtoConverter")
public class ExaminationTypeDtoConverter implements Function<ExaminationTypeDto, ExaminationType> {

  @Override
  public ExaminationType apply(final ExaminationTypeDto examinationTypeDto) {
    final CustomExaminationConverter customExaminationConverter = new CustomExaminationConverter();
    return new ExaminationType(
        examinationTypeDto.getId(),
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
          examination.getId(),
          new ExaminationType(examination.getType().getId(), examination.getType().getType()),
          new Grade(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }
}
