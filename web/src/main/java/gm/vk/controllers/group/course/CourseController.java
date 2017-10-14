package gm.vk.controllers.group.course;

import gm.vk.core.dto.group.course.CourseDto;
import gm.vk.service.group.course.CourseService;
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
@RequestMapping("/courses")
@Validated
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CourseDto>> getCourses() {
        final List<CourseDto> courses = courseService.findAll();
        return new ResponseEntity<>(courses, new HttpHeaders(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CourseDto> getCourse(@Range(min = 1) @PathVariable("id") final Integer id) {
        final CourseDto course = courseService.findOne(id);
        return new ResponseEntity<>(course, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCourse(@Valid @RequestBody final CourseDto course) {

        final CourseDto savedCourse = courseService.save(course);

        final URI createdCourseUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedCourse.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(createdCourseUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCourse(@Valid @RequestBody final CourseDto course) {

        final CourseDto savedCourse = courseService.save(course);

        final URI editedCourseUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedCourse.getId())
                        .toUri();

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(editedCourseUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CourseDto> deleteCourse(@Valid @RequestBody final CourseDto course) {
        courseService.delete(course);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CourseDto> deleteCourse(
            @Range(min = 1) @PathVariable("id") final Integer id) {
        courseService.delete(id);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

}
