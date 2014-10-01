package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
//@Repository
public class UserImplJdbc extends NamedParameterJdbcDaoSupport implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private String SQL_SELECT_USER_BY_ID = "SELECT * FROM core_users where id = ?";
    private String SQL_SELECT_USERS = "SELECT * FROM core_users";
    private String SQL_INSERT_USER = "INSERT INTO core_users (email, login, password) values(:email, :login, :password)";
    private String SQL_UPDATE_USER = "UPDATE core_users SET email = :email, login = :login, password = :password WHERE id = :id";
    private String SQL_DELETE_USER = "DELETE FROM core_users WHERE id = :id";

    public UserImplJdbc(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        LOG.trace(getClass() + " : findOne ... id = " + id);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        User user = getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_USER_BY_ID, parameters, BeanPropertyRowMapper.newInstance(User.class));
        if (user != null) {
            LOG.trace(getClass() + " : findOne. ");
            LOG.trace(getClass() + " : findOne. user = " + user);
        }
        LOG.trace(getClass() + " : findOne. Not found.");
        return user;
    }

    @Override
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        List<User> users = getNamedParameterJdbcTemplate().query(SQL_SELECT_USERS, BeanPropertyRowMapper.newInstance(User.class));
        LOG.trace(getClass() + " : findAll. ");
        return users;
    }

    @Override
    public User save(User user) {
        LOG.trace(getClass() + " : save ... ");
        LOG.trace(getClass() + " : save... user = " + user);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", user.getEmail());
        parameters.addValue("login", user.getLogin());
        parameters.addValue("password", user.getPassword());
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        User newUser = new User(user);
        if(user.getId() == null) {
            getNamedParameterJdbcTemplate().update(SQL_INSERT_USER, parameters, keyHolder);
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
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", user.getEmail());
        parameters.addValue("login", user.getLogin());
        parameters.addValue("password", user.getPassword());
        parameters.addValue("id", user.getId());
        getNamedParameterJdbcTemplate().update(SQL_UPDATE_USER, parameters);
        LOG.trace(getClass() + " : update. ");
        LOG.trace(getClass() + " : update. user = " + user);
        return user;
    }

    @Override
    public void delete(User user) {
        LOG.trace(getClass() + " : delete ... ");
        LOG.trace(getClass() + " : delete... user = " + user);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", user.getId());
        getNamedParameterJdbcTemplate().update(SQL_DELETE_USER, parameters);
        LOG.trace(getClass() + " : delete. ");
    }
}
