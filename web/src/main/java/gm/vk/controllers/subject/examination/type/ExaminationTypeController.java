package gm.vk.controllers.subject.examination.type;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import gm.vk.core.dto.user.UserDto;
import gm.vk.service.subject.examination.type.ExaminationTypeService;
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

@RestController @RequestMapping("/examination-types") @Validated public class ExaminationTypeController {

  @Autowired private ExaminationTypeService examinationService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseEntity<List<ExaminationTypeDto>> getExaminationTypes() {
    final List<ExaminationTypeDto> examinationTypes = examinationService.findAll();
    return new ResponseEntity<>(examinationTypes, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) @ResponseBody public ResponseEntity<ExaminationTypeDto> getExaminationType(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final ExaminationTypeDto examinationType = examinationService.findOne(id);
    return new ResponseEntity<>(examinationType, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> createExaminationType(
      @Valid @RequestBody final ExaminationTypeDto examinationType) {

    final ExaminationTypeDto createdExaminationType = examinationService.save(examinationType);

    final URI createdExaminationTypeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
        createdExaminationType.getId()).toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdExaminationTypeUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> editExaminationType(
      @Valid @RequestBody final ExaminationTypeDto examinationType) {

    final ExaminationTypeDto savedExaminationType = examinationService.save(examinationType);

    final URI editedExaminationTypeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
        savedExaminationType.getId()).toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedExaminationTypeUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping public ResponseEntity<UserDto> deleteExaminationType(
      @Valid @RequestBody final ExaminationTypeDto examinationType) {
    examinationService.delete(examinationType);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}") public ResponseEntity<UserDto> deleteExaminationType(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    examinationService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
