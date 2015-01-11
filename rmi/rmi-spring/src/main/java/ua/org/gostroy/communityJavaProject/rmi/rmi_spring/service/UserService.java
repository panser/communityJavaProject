package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.service;

import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;

import java.util.List;

/**
 * Created by Panov Sergey on 1/11/2015.
 */
public interface UserService {

    User findById(final Long id);
    List<User> findAll();
    User save(final User user);
    User update(final User user);
    void delete(final User user);
}
