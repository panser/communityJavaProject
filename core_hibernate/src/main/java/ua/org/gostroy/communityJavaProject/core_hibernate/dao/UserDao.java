package ua.org.gostroy.communityJavaProject.core_hibernate.dao;

import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
public interface UserDao {
    User findOne(Long id);
    List<User> findAll();
    User save(User user);
    User update(User user);
    void delete(User user);
}
