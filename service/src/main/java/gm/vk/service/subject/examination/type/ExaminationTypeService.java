package gm.vk.service.subject.examination.type;

import java.util.List;

import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;

public interface ExaminationTypeService {

  List<ExaminationTypeDto> findAll();

  ExaminationTypeDto findOne(final Integer id);

  ExaminationTypeDto save(final ExaminationTypeDto type);

  void delete(final ExaminationTypeDto type);

  void delete(final Integer id);
}
