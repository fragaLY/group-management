package gm.vk.core.dao.data.personal;

import gm.vk.core.domain.data.personal.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalDataDao extends JpaRepository<PersonalData, Integer> {

    List<PersonalData> findAll();

    PersonalData findOne(final Integer integer);

    PersonalData save(final PersonalData data);

    void delete(final PersonalData data);

    void delete(final Integer id);
}
