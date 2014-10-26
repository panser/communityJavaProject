package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

/**
 * Created by Panov Sergey on 10/26/2014.
 */
public interface UserOverCallDao {
    public User findOne(Long id);
}
