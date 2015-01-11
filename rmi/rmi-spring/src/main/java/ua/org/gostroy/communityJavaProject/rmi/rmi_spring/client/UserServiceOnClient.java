package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.client;

import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Panov Sergey on 1/12/2015.
 */
public interface UserServiceOnClient extends Remote {

    User findById(final Long id) throws RemoteException;
    List<User> findAll() throws RemoteException;
    User save(final User user) throws RemoteException;
    User update(final User user) throws RemoteException;
    void delete(final User user) throws RemoteException;
}
