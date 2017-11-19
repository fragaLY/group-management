package gm.vk.controllers.data.contacts;

import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.service.data.contacts.ContactsService;
import gm.vk.util.data.contacts.ContactsLinkBuilder;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@Validated
public class ContactsController {

  @Autowired private ContactsService contactsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ContactsDto>> getContacts() {
    final List<ContactsDto> contacts = contactsService.findAll();

        final ContactsLinkBuilder linkBuilder = new ContactsLinkBuilder();
        contacts.forEach(linkBuilder);

    return new ResponseEntity<>(contacts, new HttpHeaders(), HttpStatus.FOUND);
  }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ContactsDto> getContacts(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final ContactsDto contacts = contactsService.findOne(id);

        final ContactsLinkBuilder linkBuilder = new ContactsLinkBuilder();
        linkBuilder.accept(contacts);

    return new ResponseEntity<>(contacts, new HttpHeaders(), HttpStatus.FOUND);
  }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createContacts(@Valid @RequestBody final ContactsDto contacts) {

    final ContactsDto createdContacts = contactsService.save(contacts);

        final URI createdContactsUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdContacts.getContactsId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdContactsUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editContacts(@Valid @RequestBody final ContactsDto contacts) {

    final ContactsDto savedContacts = contactsService.save(contacts);

        final URI editedContactsUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedContacts.getContactsId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedContactsUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

    @DeleteMapping
    public ResponseEntity<ContactsDto> deleteContacts(
      @Valid @RequestBody final ContactsDto contacts) {
    contactsService.delete(contacts);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ContactsDto> deleteContacts(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    contactsService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
