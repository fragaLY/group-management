package gm.vk.controllers.person;

import gm.vk.core.dto.person.PersonDto;
import gm.vk.service.person.PersonService;
import gm.vk.util.person.PersonLinkBuilder;
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
@RequestMapping("/persons")
@Validated
public class PersonController {

  @Autowired private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PersonDto>> getPersons() {
    final List<PersonDto> persons = personService.findAll();

        final PersonLinkBuilder linkBuilder = new PersonLinkBuilder();
        persons.forEach(linkBuilder);

    return new ResponseEntity<>(persons, new HttpHeaders(), HttpStatus.FOUND);
  }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonDto> getPerson(@Range(min = 1) @PathVariable("id") final Integer id) {
    final PersonDto person = personService.findOne(id);

        final PersonLinkBuilder linkBuilder = new PersonLinkBuilder();
        linkBuilder.accept(person);

    return new ResponseEntity<>(person, new HttpHeaders(), HttpStatus.FOUND);
  }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPerson(@Valid @RequestBody final PersonDto person) {

    final PersonDto createdPerson = personService.save(person);

        final URI createdPersonUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdPerson.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdPersonUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editPerson(@Valid @RequestBody final PersonDto person) {

    final PersonDto savedPerson = personService.save(person);

        final URI editedPersonUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPerson.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedPersonUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

    @DeleteMapping
    public ResponseEntity<PersonDto> deletePerson(@Valid @RequestBody final PersonDto person) {
    personService.delete(person);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PersonDto> deletePerson(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    personService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
