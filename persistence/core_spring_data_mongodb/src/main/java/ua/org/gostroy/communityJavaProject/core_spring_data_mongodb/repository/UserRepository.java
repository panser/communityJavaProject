package ua.org.gostroy.communityJavaProject.core_spring_data_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.communityJavaProject.core_spring_data_mongodb.entity.User;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}