package gm.vk.service.group;

import gm.vk.core.dto.group.SemesterDto;

import java.util.List;

public interface SemesterService {

    List<SemesterDto> findAll();

    SemesterDto findOne(final Integer id);

    SemesterDto save(final SemesterDto semester);

    void delete(final SemesterDto semester);

    void delete(final Integer id);
}
