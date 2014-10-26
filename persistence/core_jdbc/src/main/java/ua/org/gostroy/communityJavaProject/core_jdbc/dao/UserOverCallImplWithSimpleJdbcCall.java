package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;

/**
 * Created by Panov Sergey on 10/26/2014.
 */
public class UserOverCallImplWithSimpleJdbcCall implements UserOverCallDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }


    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        LOG.trace(getClass() + " : findOne ... id = " + id);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("in_id", id);
        SimpleJdbcCall procReadUser = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FINDONE_USER")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("in_id")
                .declareParameters(
                        new SqlParameter("in_id", Types.NUMERIC),
                        new SqlOutParameter("out_login", Types.VARCHAR),
                        new SqlOutParameter("out_email", Types.VARCHAR),
                        new SqlOutParameter("out_password", Types.VARCHAR)
                );
        Map out = procReadUser.execute(params);
        User user = null;
        if (out != null) {
            user = new User();
            user.setLogin((String)out.get("out_login"));
            user.setEmail((String) out.get("out_email"));
            user.setPassword((String) out.get("out_password"));
            LOG.trace(getClass() + " : findOne. ");
        }
        else {
            LOG.trace(getClass() + " : findOne. Not found.");
        }
        return user;
    }
}
