package gm.vk.service.user.impl;

import gm.vk.core.converter.user.UserConverter;
import gm.vk.core.converter.user.UserDtoConverter;
import gm.vk.core.dao.user.UserDao;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.user.UserDto;
import gm.vk.exceptions.user.UserNotFoundException;
import gm.vk.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired private UserDao userDao;

  @Autowired private UserConverter userConverter;

  @Autowired private UserDtoConverter userDtoConverter;

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public List<UserDto> findAll() {
    return userDao
        .findAll()
        .stream()
        .filter(Objects::nonNull)
        .map(userConverter)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public UserDto findOne(@NotNull final Integer id) {

    final Optional<User> user = Optional.ofNullable(userDao.findOne(id));

    if (user.isPresent()) {
      return userConverter.apply(user.get());
    } else {
      throw new UserNotFoundException("User not found exception");
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public UserDto save(@NotNull final UserDto userDto) {
    final User savedUser = userDao.save(userDtoConverter.apply(userDto));
    return userConverter.apply(savedUser);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final UserDto userDto) {
    userDao.delete(userDtoConverter.apply(userDto));
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public void delete(@NotNull final Integer id) {
    userDao.delete(id);
  }
}
