package gm.vk.service.data.contacts.impl;

import gm.vk.core.converter.data.contacts.ContactsConverter;
import gm.vk.core.converter.data.contacts.ContactsDtoConverter;
import gm.vk.core.dao.data.contacts.ContactsDao;
import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.exceptions.data.contacts.ContactsNotFoundException;
import gm.vk.service.data.contacts.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsDao contactsDao;

    @Autowired
    private ContactsDtoConverter contactsDtoConverter;

    @Autowired
    private ContactsConverter contactsConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<ContactsDto> findAll() {
        return contactsDao.findAll().stream().filter(Objects::nonNull).map(contactsConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public ContactsDto findOne(@NotNull final Integer id) {
        final Optional<Contacts> contacts = Optional.ofNullable(contactsDao.findOne(id));

        if (contacts.isPresent()) {
            return contactsConverter.apply(contacts.get());
        } else {
            throw new ContactsNotFoundException("Contacts were not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public ContactsDto save(@NotNull final ContactsDto contacts) {
        contactsDao.save(contactsDtoConverter.apply(contacts));
        return contacts;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final ContactsDto contacts) {
        contactsDao.delete(contactsDtoConverter.apply(contacts));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        contactsDao.delete(id);
    }

}