package gm.vk.core.dao.data.contacts;

import gm.vk.core.domain.data.contacts.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsDao extends JpaRepository<Contacts, Integer> {

    List<Contacts> findAll();

    Contacts findOne(final Integer integer);

    Contacts save(final Contacts contacts);

    void delete(final Contacts contacts);

    void delete(final Integer id);
}