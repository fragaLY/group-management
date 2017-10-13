package gm.vk.controllers.subject.examination.grade;

import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.user.UserDto;
import gm.vk.service.subject.examination.grade.GradeService;
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
@RequestMapping("/grades")
@Validated
public class GradeController {

  @Autowired private GradeService gradeService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<GradeDto>> getGrades() {
    final List<GradeDto> grades = gradeService.findAll();
    return new ResponseEntity<>(grades, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<GradeDto> getGrade(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final GradeDto grade = gradeService.findOne(id);
    return new ResponseEntity<>(grade, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createGrade(@Valid @RequestBody final GradeDto grade) {

    final GradeDto createdGrade = gradeService.save(grade);

    final URI createdGradeUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdGrade.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdGradeUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editGrade(@Valid @RequestBody final GradeDto grade) {

    final GradeDto savedGrade = gradeService.save(grade);

    final URI editedGradeUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedGrade.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedGradeUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<UserDto> deleteGrade(@Valid @RequestBody final GradeDto grade) {
    gradeService.delete(grade);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<UserDto> deleteGrade(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    gradeService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
