package gm.vk.controllers.person.role;

import gm.vk.core.dto.person.role.PersonRoleDto;
import gm.vk.service.person.role.PersonRoleService;
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
@RequestMapping("/person-roles")
@Validated
public class PersonRoleController {

  @Autowired private PersonRoleService personRoleService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<PersonRoleDto>> getPersonRoles() {
    final List<PersonRoleDto> personRoles = personRoleService.findAll();
    return new ResponseEntity<>(personRoles, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<PersonRoleDto> getPersonRole(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final PersonRoleDto personRole = personRoleService.findOne(id);
    return new ResponseEntity<>(personRole, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createPersonRole(@Valid @RequestBody final PersonRoleDto personRole) {

    final PersonRoleDto createdPersonRole = personRoleService.save(personRole);

    final URI createdPersonRoleUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdPersonRole.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdPersonRoleUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editPersonRole(@Valid @RequestBody final PersonRoleDto personRole) {

    final PersonRoleDto savedPersonRole = personRoleService.save(personRole);

    final URI editedPersonRoleUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPersonRole.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedPersonRoleUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<PersonRoleDto> deletePersonRole(
      @Valid @RequestBody final PersonRoleDto personRole) {
    personRoleService.delete(personRole);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<PersonRoleDto> deletePersonRole(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    personRoleService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
