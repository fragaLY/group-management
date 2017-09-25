package gm.vk.core.dao.data.contacts.address;

import gm.vk.core.domain.data.contacts.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDao extends JpaRepository<Address, Integer> {

    List<Address> findAll();

    Address findOne(final Integer integer);

    Address save(final Address address);

    void delete(final Address address);

    void delete(final Integer id);
}