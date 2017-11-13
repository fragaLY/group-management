package gm.vk.core.dao.group.faculty;

import gm.vk.core.domain.group.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {

    List<Faculty> findAll();

    Faculty findOne(final Integer id);

    Faculty save(final Faculty faculty);

    void delete(final Faculty faculty);

    void delete(final Integer id);
}
