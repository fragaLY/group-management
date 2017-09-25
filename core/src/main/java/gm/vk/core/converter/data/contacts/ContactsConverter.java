package gm.vk.core.converter.data.contacts;

import gm.vk.core.converter.data.contacts.address.AddressConverter;
import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.dto.data.contacts.ContactsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("contactsConverter")
public class ContactsConverter implements Function<Contacts, ContactsDto> {

    @Autowired
    private AddressConverter addressConverter;

    @Override
    public ContactsDto apply(final Contacts contacts) {
        return new ContactsDto.Builder()
                .setId(contacts.getId())
                .setEmail(contacts.getEmail())
                .setPhone(contacts.getPhone())
                .setSkype(contacts.getSkype())
                .setAddress(addressConverter.apply(contacts.getAddress()))
                .build();
    }

}