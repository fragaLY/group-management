package gm.vk.controllers.data.contacts;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import gm.vk.core.dto.data.contacts.ContactsDto;
import gm.vk.service.data.contacts.ContactsService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/contacts")
@Validated
public class ContactsController {

  @Autowired private ContactsService contactsService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<ContactsDto>> getContacts() {
    final List<ContactsDto> contacts = contactsService.findAll();
    return new ResponseEntity<>(contacts, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<ContactsDto> getContacts(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final ContactsDto contacts = contactsService.findOne(id);
    return new ResponseEntity<>(contacts, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createContacts(@Valid @RequestBody final ContactsDto contacts) {

    final ContactsDto createdContacts = contactsService.save(contacts);

    final URI createdContactsUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(createdContacts.getContactsId())
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
            .path("/{id}").buildAndExpand(savedContacts.getContactsId())
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
