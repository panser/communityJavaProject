package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.webservise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.service.UserService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Panov Sergey on 1/12/2015.
 */
@Component
@WebService(serviceName="UserServiceSunJaxWs")
public class UserServiceSunJaxWsEndPoint {

    @Autowired
    UserService userService;

    @WebMethod
    public User findById(final Long id){
        return userService.findById(id);
    }

    @WebMethod
    public List<User> findAll(){
        return userService.findAll();
    }

    @WebMethod
    public User save(final User user){
        return userService.save(user);
    }

    @WebMethod
    public User update(final User user){
        return userService.update(user);
    }

    @WebMethod
    public void delete(final User user){
        userService.delete(user);
    }
}
