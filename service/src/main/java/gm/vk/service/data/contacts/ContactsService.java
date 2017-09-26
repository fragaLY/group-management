package gm.vk.service.data.contacts;

import gm.vk.core.dto.data.contacts.ContactsDto;

import java.util.List;

public interface ContactsService {

    List<ContactsDto> findAll();

    ContactsDto findOne(final Integer id);

    ContactsDto save(final ContactsDto contacts);

    void delete(final ContactsDto contacts);

    void delete(final Integer id);

}