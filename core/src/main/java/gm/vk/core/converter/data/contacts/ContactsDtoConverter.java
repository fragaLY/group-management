package gm.vk.core.converter.data.contacts;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.core.dto.person.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("contactsDtoConverter")
public class ContactsDtoConverter implements Function<ContactsDto, Contacts> {

  private static final Logger LOG = LoggerFactory.getLogger(ContactsDtoConverter.class);

  /**
   * Converts {@link ContactsDto} to {@link Contacts}
   *
   * @param contactsDto - {@link ContactsDto}
   * @return {@link Contacts}
   */
  @Override
  public Contacts apply(@NotNull final ContactsDto contactsDto) {

    LOG.info("Converts ContactsDto [{}] to Contacts", contactsDto);

    final CustomPersonDtoConverter customPersonDtoConverter = new CustomPersonDtoConverter();

      return new Contacts.Builder()
              .setId(contactsDto.getContactsId())
              .setEmail(contactsDto.getEmail())
              .setPhone(contactsDto.getPhone())
              .setSkype(contactsDto.getSkype())
              .setAddress(new CustomAddressDtoConverter().apply(contactsDto.getAddress()))
              .setPersons(
                      contactsDto
                              .getPersons()
                              .stream()
                              .map(customPersonDtoConverter)
                              .collect(Collectors.toSet()))
              .build();
  }

  private class CustomAddressDtoConverter implements Function<AddressDto, Address> {

      @Override
      public Address apply(AddressDto address) {
          return new Address.Builder()
                  .setId(address.getAddressId())
                  .setCountry(address.getCountry())
                  .setCity(address.getCity())
                  .setStreet(address.getStreet())
                  .setHome(address.getHome())
                  .setApartmentNumber(address.getApartmentNumber())
                  .build();
    }
  }

  private class CustomPersonDtoConverter implements Function<PersonDto, Person> {

      @Override
      public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getPersonId()).build();
    }
  }
}
