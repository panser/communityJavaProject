package ua.org.gostroy.communityJavaProject.core_spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}