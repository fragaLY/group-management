package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("addressDtoConverter")
public class AddressDtoConverter implements Function<AddressDto, Address> {

  @Override
  public Address apply(@NotNull final AddressDto addressDto) {
    final CustomContactDtoConverter customContactDtoConverter = new CustomContactDtoConverter();
    return new Address.Builder()
        .setId(addressDto.getId())
        .setCountry(addressDto.getCountry())
        .setCity(addressDto.getCity())
        .setStreet(addressDto.getStreet())
        .setHome(addressDto.getHome())
        .setApartmentNumber(addressDto.getApartmentNumber())
        .setContacts(
            addressDto
                .getContacts()
                .stream()
                .map(customContactDtoConverter)
                .collect(Collectors.toSet()))
        .build();
  }

  private class CustomContactDtoConverter implements Function<ContactsDto, Contacts> {

    @Override
    public Contacts apply(ContactsDto contacts) {
      return new Contacts.Builder()
          .setId(contacts.getId())
          .setSkype(contacts.getSkype())
          .setPhone(contacts.getPhone())
          .setEmail(contacts.getEmail())
          .build();
    }
  }
}
