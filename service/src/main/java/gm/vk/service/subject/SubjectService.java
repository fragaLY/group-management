package gm.vk.service.subject;

import java.util.List;

import gm.vk.core.dto.subject.SubjectDto;

public interface SubjectService {

  List<SubjectDto> findAll();

  SubjectDto findOne(final Integer id);

  SubjectDto save(final SubjectDto subject);

  void delete(final SubjectDto subject);

  void delete(final Integer id);
}
