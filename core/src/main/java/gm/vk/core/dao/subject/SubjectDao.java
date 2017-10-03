package gm.vk.core.dao.subject;

import gm.vk.core.domain.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject, Integer> {

  List<Subject> findAll();

  Subject findOne(final Integer id);

  Subject save(final Subject subject);

  void delete(final Subject subject);

  void delete(final Integer id);
}
