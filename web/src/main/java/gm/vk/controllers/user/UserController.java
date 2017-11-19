package gm.vk.controllers.user;

import gm.vk.core.dto.user.UserDto;
import gm.vk.service.user.UserService;
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
@RequestMapping("/users")
@Validated
public class UserController {

  @Autowired private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<UserDto>> getUsers() {
    final List<UserDto> users = userService.findAll();
    return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.FOUND);
  }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDto> getUser(@Range(min = 1) @PathVariable("id") final Integer id) {
    final UserDto user = userService.findOne(id);
    return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.FOUND);
  }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody final UserDto user) {

    final UserDto savedUser = userService.save(user);

        final URI createdUserUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdUserUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editUser(@Valid @RequestBody final UserDto user) {

    final UserDto savedUser = userService.save(user);

        final URI editedUserUri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedUserUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

    @DeleteMapping
    public ResponseEntity<UserDto> deleteUser(@Valid @RequestBody final UserDto user) {
    userService.delete(user);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDto> deleteUser(@Range(min = 1) @PathVariable("id") final Integer id) {
    userService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}
