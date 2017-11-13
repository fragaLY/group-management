package gm.vk.core.dao.data.contacts;

import java.util.List;

import gm.vk.core.domain.data.contacts.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsDao extends JpaRepository<Contacts, Integer> {

  /**
   * Gets all Contacts
   *
   * @return {@link List<Contacts>}
   */
  List<Contacts> findAll();

  /**
   * Gets the Contacts by id
   *
   * @param id - the id
   * @return {@link Contacts}
   */
  Contacts findOne(final Integer id);

  /**
   * Saves the contacts
   *
   * @param contacts - the contacts
   * @return {@link Contacts}
   */
  Contacts save(final Contacts contacts);

  /**
   * Deletes the contacts
   *
   * @param contacts - the contacts
   */
  void delete(final Contacts contacts);

  /**
   * Delete the Contacts by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
