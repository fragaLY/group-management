package gm.vk.controllers.group.faculty;

import gm.vk.core.dto.group.faculty.FacultyDto;
import gm.vk.service.group.faculty.FacultyService;
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
@RequestMapping("/faculties")
@Validated
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<FacultyDto>> getFaculties() {
        final List<FacultyDto> faculties = facultyService.findAll();
        return new ResponseEntity<>(faculties, new HttpHeaders(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FacultyDto> getFaculty(@Range(min = 1) @PathVariable("id") final Integer id) {
        final FacultyDto faculty = facultyService.findOne(id);
        return new ResponseEntity<>(faculty, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFaculty(@Valid @RequestBody final FacultyDto faculty) {

        final FacultyDto savedFaculty = facultyService.save(faculty);

        final URI createdFacultyUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedFaculty.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(createdFacultyUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editFaculty(@Valid @RequestBody final FacultyDto faculty) {

        final FacultyDto savedFaculty = facultyService.save(faculty);

        final URI editedFacultyUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedFaculty.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(editedFacultyUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<FacultyDto> deleteFaculty(@Valid @RequestBody final FacultyDto faculty) {
        facultyService.delete(faculty);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FacultyDto> deleteFaculty(
            @Range(min = 1) @PathVariable("id") final Integer id) {
        facultyService.delete(id);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

}