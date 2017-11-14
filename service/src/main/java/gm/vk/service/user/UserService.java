package gm.vk.service.user;

import java.util.List;

import gm.vk.core.dto.user.UserDto;

public interface UserService {

  List<UserDto> findAll();

  UserDto findOne(final Integer id);

  UserDto save(final UserDto userDto);

  void delete(final UserDto userDto);

  void delete(final Integer id);
}
