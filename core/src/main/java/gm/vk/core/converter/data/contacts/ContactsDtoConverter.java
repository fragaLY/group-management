package gm.vk.core.converter.data.contacts;

import gm.vk.core.converter.data.contacts.address.AddressDtoConverter;
import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.dto.data.contacts.ContactsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("contactsDtoConverter")
public class ContactsDtoConverter implements Function<ContactsDto, Contacts> {

    @Autowired
    private AddressDtoConverter addressDtoConverter;

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    public Contacts apply(@NotNull final ContactsDto contactsDto) {
        return new Contacts.Builder()
                .setId(contactsDto.getId())
                .setEmail(contactsDto.getEmail())
                .setPhone(contactsDto.getPhone())
                .setSkype(contactsDto.getSkype())
                .setAddress(addressDtoConverter.apply(contactsDto.getAddress()))
                .setPersons(contactsDto.getPersons().stream().map(personDtoConverter).collect(Collectors.toSet()))
                .build();
    }
}