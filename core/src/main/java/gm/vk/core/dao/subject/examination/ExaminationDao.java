package gm.vk.core.dao.subject.examination;

import gm.vk.core.domain.subject.examination.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationDao extends JpaRepository<Examination, Integer> {

    List<Examination> findAll();

    Examination findOne(final Integer id);

    Examination save(final Examination examination);

    void delete(final Examination examination);

    void delete(final Integer id);
}