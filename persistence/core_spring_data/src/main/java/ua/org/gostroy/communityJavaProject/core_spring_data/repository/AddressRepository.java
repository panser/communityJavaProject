package ua.org.gostroy.communityJavaProject.core_spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.communityJavaProject.core_spring_data.entity.AddressForAudit;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
@Repository
public interface AddressRepository  extends JpaRepository<AddressForAudit, Long> {
}
