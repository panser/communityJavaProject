package ua.org.gostroy.communityJavaProject.core_jpa_eclipselink.dao;

import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Panov Sergey on 10/29/2014.
 */
public interface UserWithCallDao {
    User procSimple(Long id);
    String funcSimple(Long id);
    List<User> procOutRef();
    BigDecimal procInArray(Long[] ids);
}
