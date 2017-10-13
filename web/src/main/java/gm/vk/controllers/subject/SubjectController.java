package gm.vk.controllers.subject;

import gm.vk.core.dto.subject.SubjectDto;
import gm.vk.service.subject.SubjectService;
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
@RequestMapping("/subjects")
@Validated
public class SubjectController {

  @Autowired private SubjectService subjectService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<SubjectDto>> getSubjects() {
    final List<SubjectDto> subjects = subjectService.findAll();
    return new ResponseEntity<>(subjects, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<SubjectDto> getSubject(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final SubjectDto subject = subjectService.findOne(id);
    return new ResponseEntity<>(subject, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createSubject(@Valid @RequestBody final SubjectDto subject) {

    final SubjectDto createdSubject = subjectService.save(subject);

    final URI createdSubjectUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdSubject.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdSubjectUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editSubject(@Valid @RequestBody final SubjectDto subject) {

    final SubjectDto savedSubject = subjectService.save(subject);

    final URI editedSubjectUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedSubject.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedSubjectUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<SubjectDto> deleteSubject(@Valid @RequestBody final SubjectDto subject) {
    subjectService.delete(subject);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<SubjectDto> deleteSubject(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    subjectService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
