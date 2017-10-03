package gm.vk.service.user;

import gm.vk.core.dto.user.UserDto;

import java.util.List;

public interface UserService {

  List<UserDto> findAll();

  UserDto findOne(final Integer id);

  UserDto save(final UserDto userDto);

  void delete(final UserDto userDto);

  void delete(final Integer id);
}
