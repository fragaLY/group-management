package gm.vk.service.subject.examination;

import gm.vk.core.dto.subject.examination.ExaminationDto;

import java.util.List;

public interface ExaminationService {

    List<ExaminationDto> findAll();

    ExaminationDto findOne(final Integer id);

    ExaminationDto save(final ExaminationDto examination);

    void delete(final ExaminationDto examination);

    void delete(final Integer id);
}
