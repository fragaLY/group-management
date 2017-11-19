package gm.vk.service.user.impl;

import gm.vk.core.converter.user.UserConverter;
import gm.vk.core.converter.user.UserDtoConverter;
import gm.vk.core.dao.user.UserDao;
import gm.vk.core.domain.user.User;
import gm.vk.core.dto.user.UserDto;
import gm.vk.exceptions.user.UserNotFoundException;
import gm.vk.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired private UserDao userDao;

  @Autowired private UserConverter userConverter;

  @Autowired private UserDtoConverter userDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<UserDto> findAll() {

    LOG.info("Gets all Users");

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

    LOG.info("Gets User by id [{}]", id);

    final Optional<User> user = Optional.ofNullable(userDao.findOne(id));

    if (user.isPresent()) {
      return userConverter.apply(user.get());
    } else {
      LOG.warn("User with id [{}] was not found", id);
      throw new UserNotFoundException("User not found exception");
    }
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public UserDto save(@NotNull final UserDto userDto) {
    final User savedUser = userDao.save(userDtoConverter.apply(userDto));
    LOG.info("User [{}] had been saved", userDto);
    return userConverter.apply(savedUser);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final UserDto userDto) {
    userDao.delete(userDtoConverter.apply(userDto));
    LOG.info("User [{}] had been deleted", userDto);
  }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
    userDao.delete(id);
    LOG.info("User with id [{}] had been deleted", id);
  }
}
