package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.dao;

import org.springframework.stereotype.Service;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;

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
