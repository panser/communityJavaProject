package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
public class UserImplJdbcOverSequence extends NamedParameterJdbcDaoSupport implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private String SQL_SELECT_USER_BY_ID = "SELECT * FROM core_users where id = ?";
    private String SQL_SELECT_USERS = "SELECT * FROM core_users";
    private String SQL_INSERT_USER = "INSERT INTO core_users (id, email, login, password) values(CORE_USERS_SEQ.NEXTVAL, :email, :login, :password)";
    private String SQL_UPDATE_USER = "UPDATE core_users SET email = :email, login = :login, password = :password WHERE id = :id";
    private String SQL_DELETE_USER = "DELETE FROM core_users WHERE id = :id";

    @Autowired
    public UserImplJdbcOverSequence(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        LOG.trace(getClass() + " : findOne ... id = " + id);
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", id);
        User user = getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_USER_BY_ID, params, BeanPropertyRowMapper.newInstance(User.class));
        if (user != null) {
            LOG.trace(getClass() + " : findOne. ");
        }
        else {
            LOG.trace(getClass() + " : findOne. Not found.");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        List<User> users = getNamedParameterJdbcTemplate().query(SQL_SELECT_USERS, BeanPropertyRowMapper.newInstance(User.class));
        LOG.trace(getClass() + " : findAll. ");
        return users;
    }

    @Override
    public User save(final User user) {
        LOG.trace(getClass() + " : save ... ");
        LOG.trace(getClass() + " : save... user = " + user);
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        User newUser = new User(user);
        if(user.getId() == null) {
            getNamedParameterJdbcTemplate().update(SQL_INSERT_USER, params, keyHolder, new String[]{"Id"});
            user.setId(keyHolder.getKey().longValue());
        }
        LOG.trace(getClass() + " : save. user = " + user);
        LOG.trace(getClass() + " : save. ");
        return user;
    }

    @Override
    public User update(User user) {
        LOG.trace(getClass() + " : update ... ");
        LOG.trace(getClass() + " : update... user = " + user);
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        getNamedParameterJdbcTemplate().update(SQL_UPDATE_USER, params);
        LOG.trace(getClass() + " : update. ");
        LOG.trace(getClass() + " : update. user = " + user);
        return user;
    }

    @Override
    public void delete(User user) {
        LOG.trace(getClass() + " : delete ... ");
        LOG.trace(getClass() + " : delete... user = " + user);
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
//        SqlParameterSource params = new MapSqlParameterSource()
//            .addValue("id", user.getId());
        getNamedParameterJdbcTemplate().update(SQL_DELETE_USER, params);
        LOG.trace(getClass() + " : delete. ");
    }
}
