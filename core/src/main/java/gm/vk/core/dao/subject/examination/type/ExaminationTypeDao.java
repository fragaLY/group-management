package gm.vk.core.dao.subject.examination.type;

import gm.vk.core.domain.subject.examination.type.ExaminationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationTypeDao extends JpaRepository<ExaminationType, Integer> {

    List<ExaminationType> findAll();

    ExaminationType findOne(final Integer id);

    ExaminationType save(final ExaminationType type);

    void delete(final ExaminationType type);

    void delete(final Integer id);
}