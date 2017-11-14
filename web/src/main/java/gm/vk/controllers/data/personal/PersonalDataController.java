package gm.vk.controllers.data.personal;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import gm.vk.core.dto.data.personal.PersonalDataDto;
import gm.vk.service.data.personal.PersonalDataService;
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

@RestController @RequestMapping("/personal-data") @Validated public class PersonalDataController {

  @Autowired private PersonalDataService personalDataService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseEntity<List<PersonalDataDto>> getPersonalDatas() {
    final List<PersonalDataDto> personalDatas = personalDataService.findAll();
    return new ResponseEntity<>(personalDatas, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseEntity<PersonalDataDto> getPersonalData(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final PersonalDataDto personalData = personalDataService.findOne(id);
    return new ResponseEntity<>(personalData, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> createPersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {

    final PersonalDataDto createdPersonalData = personalDataService.save(personalData);

    final URI createdPersonalDataUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
        createdPersonalData.getId()).toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdPersonalDataUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> editPersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {

    final PersonalDataDto savedPersonalData = personalDataService.save(personalData);

    final URI editedPersonalDataUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
        savedPersonalData.getId()).toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedPersonalDataUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping public ResponseEntity<PersonalDataDto> deletePersonalData(
      @Valid @RequestBody final PersonalDataDto personalData) {
    personalDataService.delete(personalData);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}") public ResponseEntity<PersonalDataDto> deletePersonalData(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    personalDataService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
