package gm.vk.core.converter.data.contacts.address;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("addressConverter") public class AddressConverter implements Function<Address, AddressDto> {

  private static final Logger LOG = LoggerFactory.getLogger(AddressConverter.class);

  /**
   * Converts {@link Address} to {@link AddressDto}
   *
   * @param address - the {@link Address}
   * @return {@link AddressDto}
   */
  @Override public AddressDto apply(@NotNull final Address address) {

    LOG.info("Converts Address [{}] to AddressDto.", address);

    final CustomContactConverter customContactConverter = new CustomContactConverter();
    final Set<Contacts> contacts = address.getContacts();
    Set<ContactsDto> contactsDtos = null;

    if (contacts != null) {
      contactsDtos = contacts.stream().map(customContactConverter).collect(Collectors.toSet());
    }

    return new AddressDto.Builder().setId(address.getId()).setCountry(address.getCountry()).setCity(address.getCity()).setStreet(
        address.getStreet()).setHome(address.getHome()).setApartmentNumber(address.getApartmentNumber()).setContacts(
        contactsDtos).build();
  }

  private class CustomContactConverter implements Function<Contacts, ContactsDto> {

    @Override public ContactsDto apply(Contacts contacts) {
      return new ContactsDto.Builder().setId(contacts.getId()).setSkype(contacts.getSkype()).setPhone(contacts.getPhone()).setEmail(
          contacts.getEmail()).build();
    }
  }
}
