package gm.vk.core.converter.data.contacts;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.domain.person.Person;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.core.dto.person.PersonDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("contactsDtoConverter")
public class ContactsDtoConverter implements Function<ContactsDto, Contacts> {

  @Override
  public Contacts apply(@NotNull final ContactsDto contactsDto) {
    final CustomPersonDtoConverter customPersonDtoConverter = new CustomPersonDtoConverter();

    return new Contacts.Builder()
        .setId(contactsDto.getId())
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
          .setId(address.getId())
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
      return new Person.Builder().setId(person.getId()).build();
    }
  }
}
