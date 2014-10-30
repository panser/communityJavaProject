package ua.org.gostroy.communityJavaProject.core_jdbc.dao;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    public User procSimple(Long id) {
        LOG.trace(getClass() + " : procSimple ... ");
        LOG.trace(getClass() + " : procSimple ... id = " + id);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("in_id", id);
        SimpleJdbcCall procReadUser = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_SIMPLE")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("in_id")
                .declareParameters(
                        new SqlParameter("in_id", Types.NUMERIC),
                        new SqlOutParameter("out_email", Types.VARCHAR),
                        new SqlOutParameter("out_login", Types.VARCHAR),
                        new SqlOutParameter("out_password", Types.VARCHAR)
                );
        Map out = procReadUser.execute(params);

        User user = new User();
        user.setId(id);
        user.setLogin((String)out.get("out_login"));
        user.setEmail((String) out.get("out_email"));
        user.setPassword((String) out.get("out_password"));
        LOG.trace(getClass() + " : procSimple. ");
        return user;
    }

    @Override
    public String funcSimple(Long id) {
        LOG.trace(getClass() + " : funcSimple ... ");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("in_id", id);
        SimpleJdbcCall funcReadUser = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FUNC_SIMPLE");
        String fullInfo = funcReadUser.executeFunction(String.class, params);

        LOG.trace(getClass() + " : funcSimple. ");
        return fullInfo;
    }

    @Override
    public List<User> procOutRef() {
        LOG.trace(getClass() + " : procOutRef ... ");
        SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_OUT_REF")
                .returningResultSet("users",
                        BeanPropertyRowMapper.newInstance(User.class)
                );
        Map out = proc.execute();
//        Map out = proc.execute(new HashMap<String, Object>(0));
        return (List) out.get("users");
    }

    @Override
    public BigDecimal procInArray(final Long[] ids) {
        SimpleJdbcCall procReadUser = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_IN_ARRAY")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("in_id")
                .declareParameters(
                        new SqlParameter("in_user_ids", Types.ARRAY),
                        new SqlOutParameter("result_proc", Types.NUMERIC)
                );

        SqlTypeValue sqlTypeValue = new AbstractSqlTypeValue() {
            @Override
            protected Object createTypeValue(Connection conn, int sqlType, String typeName) throws SQLException {
                ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("USER_ID_ARRAY", conn);
                ARRAY idArray = new ARRAY(arrayDescriptor, conn, ids);
                return idArray;
            }
        };
        Map in = Collections.singletonMap("in_user_ids", sqlTypeValue);
//        Map in = Collections.singletonMap("in_user_ids", new SqlArrayValue(ids));  //need spring-data-oracle

        Map out = procReadUser.execute(in);

        BigDecimal result = (BigDecimal)out.get("result_proc");
        return result;
    }
}
