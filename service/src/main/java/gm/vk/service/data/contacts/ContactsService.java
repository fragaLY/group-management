package gm.vk.service.data.contacts;

import java.util.List;

import gm.vk.core.dto.data.contacts.ContactsDto;

public interface ContactsService {

  List<ContactsDto> findAll();

  ContactsDto findOne(final Integer id);

  ContactsDto save(final ContactsDto contacts);

  void delete(final ContactsDto contacts);

  void delete(final Integer id);
}
