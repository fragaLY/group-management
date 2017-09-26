package gm.vk.service.subject.examination.grade;

import gm.vk.core.dto.subject.examination.grade.GradeDto;

import java.util.List;

public interface GradeService {

    List<GradeDto> findAll();

    GradeDto findOne(final Integer id);

    GradeDto save(final GradeDto grade);

    void delete(final GradeDto grade);

    void delete(final Integer id);
}