package gm.vk.core.dao.group.course;

import gm.vk.core.domain.group.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

  /**
   * Gets all courses
   *
   * @return {@link List<Course>}
   */
  List<Course> findAll();

  /**
   * Gets Course by id
   *
   * @param id - the id
   * @return {@link Course}
   */
  Course findOne(final Integer id);

  /**
   * Saves course
   *
   * @param course - the course
   * @return {@link Course}
   */
  Course save(final Course course);

  /**
   * Deletes course
   *
   * @param course - the course
   */
  void delete(final Course course);

  /**
   * Deletes course by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
