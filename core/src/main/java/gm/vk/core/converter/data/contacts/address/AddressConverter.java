package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("addressConverter")
public class AddressConverter implements Function<Address, AddressDto> {

  @Autowired private PersonConverter personConverter;

  @Override
  public AddressDto apply(@NotNull final Address address) {
    final CustomContactConverter customContactConverter = new CustomContactConverter();
    final Set<Contacts> contacts = address.getContacts();
    Set<ContactsDto> contactsDtos = null;

    if (contacts != null) {
      contactsDtos = contacts.stream().map(customContactConverter).collect(Collectors.toSet());
    }

    return new AddressDto.Builder()
        .setId(address.getId())
        .setCountry(address.getCountry())
        .setCity(address.getCity())
        .setStreet(address.getStreet())
        .setHome(address.getHome())
            .setApartmentNumber(address.getApartmentNumber())
            .setContacts(contactsDtos)
        .build();
  }

  private class CustomContactConverter implements Function<Contacts, ContactsDto> {

    @Override
    public ContactsDto apply(Contacts contacts) {
      return new ContactsDto.Builder()
          .setId(contacts.getId())
          .setSkype(contacts.getSkype())
          .setPhone(contacts.getPhone())
          .setEmail(contacts.getEmail())
          .build();
    }
  }
}
