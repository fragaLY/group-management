package gm.vk.core.converter.data.contacts.address;

import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("addressConverter")
public class AddressConverter implements Function<Address, AddressDto> {

    @Override
    public AddressDto apply(final Address address) {
        return new AddressDto.Builder()
                .setId(address.getId())
                .setCountry(address.getCountry())
                .setCity(address.getCity())
                .setStreet(address.getStreet())
                .setHome(address.getHome())
                .build();
    }

}