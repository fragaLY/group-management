package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("addressDtoConverter")
public class AddressDtoConverter implements Function<AddressDto, Address> {

    @Override
    public Address apply(final AddressDto addressDto) {
        return new Address.Builder()
                .setId(addressDto.getId())
                .setCountry(addressDto.getCountry())
                .setCity(addressDto.getCity())
                .setStreet(addressDto.getStreet())
                .setHome(addressDto.getHome())
                .setApartmentNumber(addressDto.getApartmentNumber())
                .build();
    }
}