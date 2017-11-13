package gm.vk.core.dao.group;

import gm.vk.core.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupDao extends JpaRepository<Group, Integer> {

    List<Group> findAll();

    Group findOne(final Integer id);

    Group save(final Group group);

    void delete(final Group group);

    void delete(final Integer id);
}
