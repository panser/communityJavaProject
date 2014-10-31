package ua.org.gostroy.communityJavaProject.core_hibernate.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Panov Sergey on 10/29/2014.
 */
public class UserWithCallImplOverNamedNativeQuery implements UserWithCallDao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public User procSimple(Long id) {
/*
        OUT parameter must be the first parameter in your procedure
         and it should be a SYS_REFCURSOR.
         Else it would not work.

         create or replace PROCEDURE         MYSYS_P_MY_TEST_PROC
    (
        p_recordset      OUT SYS_REFCURSOR,
        p_airport_iata_id IN varchar2  ,
        p_flight_number   IN varchar2  ,
        p_flight_dep_date IN varchar2
    )

    http://dinukaroshan.blogspot.com/2010/09/stored-procedures-with-hibernate.html
*/

        Query query = sessionFactory.getCurrentSession().getNamedQuery("procSimpleNative");
        query.setParameter("in_id",id);

        int resultCode = query.executeUpdate();

        String email = (String)query.getNamedParameters()[0];
        String login = (String)query.getNamedParameters()[1];
        String password = (String)query.getNamedParameters()[2];

        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    @Override
    public String funcSimple(Long id) {
        return null;
    }

    @Override
    public List<User> procOutRef() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("procOutRefNative");
        List<User> result = query.list();
        return result;
    }

    @Override
    public BigDecimal procInArray(Long[] ids) {
        return null;
    }
}
