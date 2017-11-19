package gm.vk.service.group.faculty;

import gm.vk.core.dto.group.faculty.FacultyDto;

import java.util.List;

public interface FacultyService {

  List<FacultyDto> findAll();

  FacultyDto findOne(final Integer id);

  FacultyDto save(final FacultyDto faculty);

  void delete(final FacultyDto faculty);

  void delete(final Integer id);
}
