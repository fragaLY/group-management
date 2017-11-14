package gm.vk.core.dao.user;

import java.util.List;

import gm.vk.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

  /**
   * Gets all users
   *
   * @return {@link List<User>}
   */
  List<User> findAll();

  /**
   * Gets the User by id
   *
   * @param id - the id
   * @return {@link User}
   */
  User findOne(final Integer id);

  /**
   * Saves user
   *
   * @param user - the user
   * @return {@link User}
   */
  User save(final User user);

  /**
   * Deletes user
   *
   * @param user - the user
   */
  void delete(final User user);

  /**
   * Deletes the user by id
   *
   * @param id - the id
   */
  void delete(final Integer id);
}
