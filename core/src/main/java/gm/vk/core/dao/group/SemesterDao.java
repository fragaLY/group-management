package gm.vk.core.dao.group;

import gm.vk.core.domain.group.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterDao extends JpaRepository<Semester, Integer> {

    /**
     * Gets all semesters
     *
     * @return {@link List<Semester>}
     */
    List<Semester> findAll();

    /**
     * Gets the semester by id
     *
     * @param id - the id
     * @return {@link Semester}
     */
    Semester findOne(final Integer id);

    /**
     * Saves semester
     *
     * @param semester - the semester
     * @return {@link Semester}
     */
    Semester save(final Semester semester);

    /**
     * Deletes semester
     *
     * @param semester - the semester
     */
    void delete(final Semester semester);

    /**
     * Deletes the semester by id
     *
     * @param id - the id
     */
    void delete(final Integer id);
}
