package gm.vk.core.dao.group.course;

import gm.vk.core.domain.group.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

    List<Course> findAll();

    Course findOne(final Integer id);

    Course save(final Course course);

    void delete(final Course course);

    void delete(final Integer id);
}
