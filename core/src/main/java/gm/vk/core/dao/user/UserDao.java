package gm.vk.core.dao.user;

import gm.vk.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

  List<User> findAll();

  User findOne(final Integer id);

  User save(final User user);

  void delete(final User user);

  void delete(final Integer id);
}
