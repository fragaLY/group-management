package gm.vk.core.dao.group;

import gm.vk.core.domain.group.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterDao extends JpaRepository<Semester, Integer> {

    List<Semester> findAll();

    Semester findOne(final Integer id);

    Semester save(final Semester semester);

    void delete(final Semester semester);

    void delete(final Integer id);
}
