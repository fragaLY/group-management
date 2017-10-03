package gm.vk.core.converter.subject.examination;

import gm.vk.core.domain.subject.examination.Examination;
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
    return new ExaminationDto(
        examination.getId(),
        new ExaminationTypeDto(examination.getType().getId(), examination.getType().getType()),
        new GradeDto(examination.getGrade().getId(), examination.getGrade().getGrade()));
  }
}
