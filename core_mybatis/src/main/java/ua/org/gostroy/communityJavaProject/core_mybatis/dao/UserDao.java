package ua.org.gostroy.communityJavaProject.core_mybatis.dao;

import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
public interface UserDao {
    User findOne(Long id);
    List<User> findAll();
    Long save(User user);
    Long update(User user);
    void delete(User user);
}
