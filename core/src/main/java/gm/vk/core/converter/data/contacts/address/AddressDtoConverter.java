package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("addressDtoConverter")
public class AddressDtoConverter implements Function<AddressDto, Address> {

  private static final Logger LOG = LoggerFactory.getLogger(AddressDtoConverter.class);

  /**
   * Converts {@link AddressDto} to {@link Address}
   *
   * @param addressDto - the {@link AddressDto}
   * @return {@link Address}
   */
  @Override
  public Address apply(@NotNull final AddressDto addressDto) {

    LOG.info("Converts AddressDto [{}] to Address.", addressDto);

    final CustomContactDtoConverter customContactDtoConverter = new CustomContactDtoConverter();
    final Set<ContactsDto> contactsDtos = addressDto.getContacts();
    Set<Contacts> contacts = null;

    if (contactsDtos != null) {
        contacts =
                contactsDtos
                        .stream()
                        .filter(Objects::nonNull)
                        .map(customContactDtoConverter)
                        .collect(Collectors.toSet());
    }

      return new Address.Builder()
              .setId(addressDto.getAddressId())
              .setCountry(addressDto.getCountry())
              .setCity(addressDto.getCity())
              .setStreet(addressDto.getStreet())
              .setHome(addressDto.getHome())
              .setApartmentNumber(addressDto.getApartmentNumber())
              .setContacts(contacts)
              .build();
  }

  private class CustomContactDtoConverter implements Function<ContactsDto, Contacts> {

      @Override
      public Contacts apply(ContactsDto contacts) {
          return new Contacts.Builder()
                  .setId(contacts.getContactsId())
                  .setSkype(contacts.getSkype())
                  .setPhone(contacts.getPhone())
                  .setEmail(contacts.getEmail())
                  .build();
    }
  }
}
