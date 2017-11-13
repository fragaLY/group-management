package gm.vk.core.converter.data.contacts;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.core.dto.person.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("contactsConverter")
public class ContactsConverter implements Function<Contacts, ContactsDto> {

  private static final Logger LOG = LoggerFactory.getLogger(ContactsConverter.class);

  /**
   * Converts {@link Contacts} to {@link ContactsDto}
   *
   * @param contacts - {@link Contacts}
   * @return {@link ContactsDto}
   */
  @Override public ContactsDto apply(@NotNull final Contacts contacts) {

    LOG.info("Converts Contacts [{}] to ContactsDto", contacts);

    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();

    return new ContactsDto.Builder().setId(contacts.getId()).setEmail(contacts.getEmail()).setPhone(contacts.getPhone()).setSkype(contacts.getSkype()).setAddress(new CustomAddressConverter().apply(contacts.getAddress())).setPersons(
        contacts.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet())).build();
  }

  private class CustomAddressConverter implements Function<Address, AddressDto> {

    @Override
    public AddressDto apply(Address address) {
      return new AddressDto.Builder()
          .setId(address.getId())
          .setCountry(address.getCountry())
          .setCity(address.getCity())
          .setStreet(address.getStreet())
          .setHome(address.getHome())
          .setApartmentNumber(address.getApartmentNumber())
          .build();
    }
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override
    public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }
}
