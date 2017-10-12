package gm.vk.controllers.user;

import gm.vk.core.dto.user.UserDto;
import gm.vk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<UserDto>> getAllClients() {

    List<UserDto> users = userService.findAll();
    final HttpHeaders responseHeader = new HttpHeaders();

    return new ResponseEntity<>(users, responseHeader, HttpStatus.FOUND);
  }
}
