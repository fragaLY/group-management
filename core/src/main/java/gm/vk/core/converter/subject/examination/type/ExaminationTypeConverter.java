package gm.vk.core.converter.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;
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
      return new ExaminationDto(
          examination.getId(),
          new ExaminationTypeDto(examination.getType().getId(), examination.getType().getType()),
          new GradeDto(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }
}
