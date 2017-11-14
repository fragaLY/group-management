package gm.vk.service.subject.examination.grade;

import java.util.List;

import gm.vk.core.dto.subject.examination.grade.GradeDto;

public interface GradeService {

  List<GradeDto> findAll();

  GradeDto findOne(final Integer id);

  GradeDto save(final GradeDto grade);

  void delete(final GradeDto grade);

  void delete(final Integer id);
}
