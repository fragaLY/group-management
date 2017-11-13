package gm.vk.core.dao.data.contacts.address;

import java.util.List;

import gm.vk.core.domain.data.contacts.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Integer> {

  /**
   * Gets all addresses
   *
   * @return the {@link List<Address>}
   */
  List<Address> findAll();

  /**
   * Gets the address by id
   *
   * @param id - the id
   * @return {@link Address}
   */
  Address findOne(final Integer id);

  /**
   * Saves the address
   *
   * @param address - the {@link Address}
   * @return {@link Address}
   */
  Address save(final Address address);

  /**
   * Deletes the address
   *
   * @param address - the address
   */
  void delete(final Address address);

  /**
   * Deletes the address by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
