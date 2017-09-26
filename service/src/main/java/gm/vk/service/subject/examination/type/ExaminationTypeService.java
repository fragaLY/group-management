package gm.vk.service.subject.examination.type;

import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;

import java.util.List;

public interface ExaminationTypeService {

    List<ExaminationTypeDto> findAll();

    ExaminationTypeDto findOne(final Integer id);

    ExaminationTypeDto save(final ExaminationTypeDto type);

    void delete(final ExaminationTypeDto type);

    void delete(final Integer id);
}
