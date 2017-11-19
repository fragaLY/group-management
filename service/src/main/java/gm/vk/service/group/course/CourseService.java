package gm.vk.service.group.course;

import gm.vk.core.dto.group.course.CourseDto;

import java.util.List;

public interface CourseService {

  List<CourseDto> findAll();

  CourseDto findOne(final Integer id);

  CourseDto save(final CourseDto course);

  void delete(final CourseDto course);

  void delete(final Integer id);
}
