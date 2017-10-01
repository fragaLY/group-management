package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.converter.data.contacts.ContactsConverter;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("addressConverter")
public class AddressConverter implements Function<Address, AddressDto> {

    @Autowired
    private ContactsConverter contactsConverter;

    @Override
    public AddressDto apply(@NotNull final Address address) {
        return new AddressDto.Builder()
                .setId(address.getId())
                .setCountry(address.getCountry())
                .setCity(address.getCity())
                .setStreet(address.getStreet())
                .setHome(address.getHome())
                .setContacts(address.getContacts().stream().map(contactsConverter).collect(Collectors.toSet()))
                .build();
    }

}