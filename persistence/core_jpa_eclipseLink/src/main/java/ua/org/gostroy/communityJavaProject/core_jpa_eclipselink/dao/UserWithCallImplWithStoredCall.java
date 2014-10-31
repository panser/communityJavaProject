package ua.org.gostroy.communityJavaProject.core_jpa_eclipselink.dao;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DataModifyQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Panov Sergey on 10/31/2014.
 */
public class UserWithCallImplWithStoredCall implements UserWithCallDao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private JpaEntityManager em;
//    private EntityManager em;

    @Override
    public User procSimple(Long id) {
        StoredProcedureCall spcall = new StoredProcedureCall();
        spcall.setProcedureName("PROC_SIMPLE");
        spcall.addNamedArgument("in_id", "in_id");
        spcall.addNamedOutputArgument("out_email", "out_email", String.class);
        spcall.addNamedOutputArgument("out_login", "out_login", String.class);
        spcall.addNamedOutputArgument("out_password", "out_password", String.class);

//        DataModifyQuery query = new DataModifyQuery();
        ValueReadQuery query = new ValueReadQuery();
        query.setCall(spcall);
        query.addArgument("in_id");   // input
        query.addArgument("out_email");   // input
        query.addArgument("out_login");   // input
        query.addArgument("out_password");   // input

        List args = new ArrayList();
        args.add(id);
        em.getSession().executeQuery(query, args);

        return null;
    }

    @Override
    public String funcSimple(Long id) {
        return null;
    }

    @Override
    public List<User> procOutRef() {
        StoredProcedureCall spcall = new StoredProcedureCall();
        spcall.setProcedureName("PROC_OUT_REF");
        spcall.useNamedCursorOutputAsResultSet("users");

        Session session = em.getSession();
        em.getSession().executeSelectingCall(spcall);
        List result = (List) em.getSession().executeSelectingCall(spcall);
        return result;
    }

    @Override
    public BigDecimal procInArray(Long[] ids) {
        return null;
    }
}
