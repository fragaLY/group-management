package gm.vk.controllers.group;

import gm.vk.core.dto.group.SemesterDto;
import gm.vk.service.group.SemesterService;
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
@RequestMapping("/semesters")
@Validated
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<SemesterDto>> getSemesters() {
        final List<SemesterDto> semesters = semesterService.findAll();
        return new ResponseEntity<>(semesters, new HttpHeaders(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SemesterDto> getSemester(
            @Range(min = 1) @PathVariable("id") final Integer id) {
        final SemesterDto semester = semesterService.findOne(id);
        return new ResponseEntity<>(semester, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSemester(@Valid @RequestBody final SemesterDto semester) {

        final SemesterDto savedSemester = semesterService.save(semester);

        final URI createdSemesterUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedSemester.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(createdSemesterUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editSemester(@Valid @RequestBody final SemesterDto semester) {

        final SemesterDto savedSemester = semesterService.save(semester);

        final URI editedSemesterUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedSemester.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(editedSemesterUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<SemesterDto> deleteSemester(
            @Valid @RequestBody final SemesterDto semester) {
        semesterService.delete(semester);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SemesterDto> deleteSemester(
            @Range(min = 1) @PathVariable("id") final Integer id) {
        semesterService.delete(id);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
