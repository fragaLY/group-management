package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.converter.data.contacts.ContactsDtoConverter;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("addressDtoConverter")
public class AddressDtoConverter implements Function<AddressDto, Address> {

    @Autowired
    private ContactsDtoConverter contactsDtoConverter;

    @Override
    public Address apply(@NotNull final AddressDto addressDto) {
        return new Address.Builder()
                .setId(addressDto.getId())
                .setCountry(addressDto.getCountry())
                .setCity(addressDto.getCity())
                .setStreet(addressDto.getStreet())
                .setHome(addressDto.getHome())
                .setApartmentNumber(addressDto.getApartmentNumber())
                .setContacts(addressDto.getContacts().stream().map(contactsDtoConverter).collect(Collectors.toSet()))
                .build();
    }
}