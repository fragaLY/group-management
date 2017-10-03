package gm.vk.core.converter.subject.examination;

import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("examinationDtoConverter")
public class ExaminationDtoConverter implements Function<ExaminationDto, Examination> {

  @Override
  public Examination apply(@NotNull final ExaminationDto examinationDto) {
    return new Examination(
        examinationDto.getId(),
        new ExaminationType(examinationDto.getType().getId(), examinationDto.getType().getType()),
        new Grade(examinationDto.getGrade().getId(), examinationDto.getGrade().getGrade()));
  }
}
