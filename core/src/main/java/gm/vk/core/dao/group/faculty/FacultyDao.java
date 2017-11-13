package gm.vk.core.dao.group.faculty;

import java.util.List;

import gm.vk.core.domain.group.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {

    /**
     * Gets all faculties
     *
     * @return {@link List<Faculty>}
     */
    List<Faculty> findAll();

    /**
     * Gets Faculty by id
     *
     * @param id - the id
     * @return {@link Faculty}
     */
    Faculty findOne(final Integer id);

    /**
     * Saves faculty
     *
     * @param faculty - the faculty
     * @return {@link Faculty}
     */
    Faculty save(final Faculty faculty);

    /**
     * Deletes the faculty
     *
     * @param faculty - the faculty
     */
    void delete(final Faculty faculty);

    /**
     * Deletes the faculty by id
     *
     * @param id - the id
     */
    void delete(final Integer id);
}
