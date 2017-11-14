package gm.vk.service.subject.examination;

import java.util.List;

import gm.vk.core.dto.subject.examination.ExaminationDto;

public interface ExaminationService {

  List<ExaminationDto> findAll();

  ExaminationDto findOne(final Integer id);

  ExaminationDto save(final ExaminationDto examination);

  void delete(final ExaminationDto examination);

  void delete(final Integer id);
}
