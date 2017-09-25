package gm.vk.core.dao.subject.examination.grade;

import gm.vk.core.domain.subject.examination.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeDao extends JpaRepository<Grade, Integer> {

    List<Grade> findAll();

    Grade findOne(final Integer integer);

    Grade save(final Grade grade);

    void delete(final Grade grade);

    void delete(final Integer id);

}
