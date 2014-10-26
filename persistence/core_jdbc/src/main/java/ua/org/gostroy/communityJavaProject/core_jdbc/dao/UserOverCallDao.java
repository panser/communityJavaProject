package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

/**
 * Created by Panov Sergey on 10/26/2014.
 */
public interface UserOverCallDao {
    User procSimple(Long id);
    String funcSimple(Long id);
}
