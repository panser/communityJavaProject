package ua.org.gostroy.communityJavaProject.core_spring_data.entity;

import org.hibernate.annotations.Type;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
@MappedSuperclass
public class AbstractAuditableEntity extends AbstractPersistable<Long> implements Auditable<String, Long> {

    @Version
    private Long version;
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastUpdated;
    private String lastUpdateUser;
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;
    private String createUser;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String getCreatedBy() {
        return createUser;
    }

    @Override
    public void setCreatedBy(String s) {
        this.createUser = s;
    }

    @Override
    public DateTime getCreatedDate() {
        return created;
    }

    @Override
    public void setCreatedDate(DateTime dateTime) {
        this.created = dateTime;
    }

    @Override
    public String getLastModifiedBy() {
        return lastUpdateUser;
    }

    @Override
    public void setLastModifiedBy(String s) {
        this.lastUpdateUser = s;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return lastUpdated;
    }

    @Override
    public void setLastModifiedDate(DateTime dateTime) {
        this.lastUpdated = dateTime;
    }

    @Override
    public String toString() {
        return "AbstractAuditableEntity{" +
                "lastUpdated=" + lastUpdated +
                ", lastUpdateUser='" + lastUpdateUser + '\'' +
                ", created=" + created +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
