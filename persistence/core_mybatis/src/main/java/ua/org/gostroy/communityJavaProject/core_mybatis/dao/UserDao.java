package ua.org.gostroy.communityJavaProject.core_mybatis.dao;

import org.apache.ibatis.annotations.Param;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
public interface UserDao {
    User findOne(@Param("id") Long id);
    List<User> findAll();
    Long save(User user);
    Long update(User user);
    void delete(User user);
}
