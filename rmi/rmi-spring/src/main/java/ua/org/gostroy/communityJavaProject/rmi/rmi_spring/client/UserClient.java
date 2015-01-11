package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.service.UserService;

import java.util.List;

/**
 * Created by Panov Sergey on 1/11/2015.
 */
public class UserClient {

    @Autowired
    UserService userService;

    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    public User update(User user){
        User updateUser = userService.update(user);
        return updateUser;
    }

    public User save(User user){
        User saveUser = userService.save(user);
        return saveUser;
    }

    public void delete(User user){
        userService.delete(user);
    }

}
