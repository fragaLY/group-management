package gm.vk.controllers.data.personal;

import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.service.data.personal.PersonalDataService;
import gm.vk.util.data.personal.PersonalDataLinkBuilder;
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
@RequestMapping("/personal-data")
@Validated
public class PersonalDataController {

  @Autowired private PersonalDataService personalDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PersonalDataDto>> getAllPersonalData() {
        final List<PersonalDataDto> personalData = personalDataService.findAll();

        final PersonalDataLinkBuilder linkBuilder = new PersonalDataLinkBuilder();
        personalData.forEach(linkBuilder);

        return new ResponseEntity<>(personalData, new HttpHeaders(), HttpStatus.FOUND);
  }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonalDataDto> getPersonalData(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final PersonalDataDto personalData = personalDataService.findOne(id);

        final PersonalDataLinkBuilder linkBuilder = new PersonalDataLinkBuilder();
        linkBuilder.accept(personalData);

    return new ResponseEntity<>(personalData, new HttpHeaders(), HttpStatus.FOUND);
  }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {

    final PersonalDataDto createdPersonalData = personalDataService.save(personalData);

        final URI createdPersonalDataUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdPersonalData.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdPersonalDataUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editPersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {

    final PersonalDataDto savedPersonalData = personalDataService.save(personalData);

        final URI editedPersonalDataUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPersonalData.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedPersonalDataUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

    @DeleteMapping
    public ResponseEntity<PersonalDataDto> deletePersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {
    personalDataService.delete(personalData);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PersonalDataDto> deletePersonalData(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    personalDataService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
