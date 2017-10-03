package gm.vk.service.subject;

import gm.vk.core.dto.subject.SubjectDto;

import java.util.List;

public interface SubjectService {

  List<SubjectDto> findAll();

  SubjectDto findOne(final Integer id);

  SubjectDto save(final SubjectDto subject);

  void delete(final SubjectDto subject);

  void delete(final Integer id);
}
