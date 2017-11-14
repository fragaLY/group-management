package gm.vk.service.group.course;

import java.util.List;

import gm.vk.core.dto.group.course.CourseDto;

public interface CourseService {

  List<CourseDto> findAll();

  CourseDto findOne(final Integer id);

  CourseDto save(final CourseDto course);

  void delete(final CourseDto course);

  void delete(final Integer id);
}
