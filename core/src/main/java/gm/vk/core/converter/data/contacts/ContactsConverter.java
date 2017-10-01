package gm.vk.core.converter.data.contacts;

import gm.vk.core.converter.data.contacts.address.AddressConverter;
import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.dto.data.contacts.ContactsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("contactsConverter")
public class ContactsConverter implements Function<Contacts, ContactsDto> {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PersonConverter personConverter;

    @Override
    public ContactsDto apply(@NotNull final Contacts contacts) {
        return new ContactsDto.Builder()
                .setId(contacts.getId())
                .setEmail(contacts.getEmail())
                .setPhone(contacts.getPhone())
                .setSkype(contacts.getSkype())
                .setAddress(addressConverter.apply(contacts.getAddress()))
                .setPersons(contacts.getPersons().stream().map(personConverter).collect(Collectors.toSet()))
                .build();
    }

}