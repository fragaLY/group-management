package gm.vk.controllers.subject.examination;

import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.user.UserDto;
import gm.vk.service.subject.examination.ExaminationService;
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
@RequestMapping("/examinations")
@Validated
public class ExaminationController {

  @Autowired private ExaminationService examinationService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<ExaminationDto>> getExaminations() {
    final List<ExaminationDto> examinations = examinationService.findAll();
    return new ResponseEntity<>(examinations, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<ExaminationDto> getExamination(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final ExaminationDto examination = examinationService.findOne(id);
    return new ResponseEntity<>(examination, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createExamination(@Valid @RequestBody final ExaminationDto examination) {

    final ExaminationDto createdExamination = examinationService.save(examination);

    final URI createdExaminationUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdExamination.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdExaminationUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editExamination(@Valid @RequestBody final ExaminationDto examination) {

    final ExaminationDto savedExamination = examinationService.save(examination);

    final URI editedExaminationUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedExamination.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedExaminationUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<UserDto> deleteExamination(
      @Valid @RequestBody final ExaminationDto examination) {
    examinationService.delete(examination);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<UserDto> deleteExamination(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    examinationService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
