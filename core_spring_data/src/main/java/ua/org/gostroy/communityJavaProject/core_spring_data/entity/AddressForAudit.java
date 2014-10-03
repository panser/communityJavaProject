package ua.org.gostroy.communityJavaProject.core_spring_data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
@Entity
public class AddressForAudit extends AbstractAuditableEntity{
    private String address = null;
    private String city = null;
    private String state = null;
    private String zipPostal = null;
    private String country = null;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipPostal() {
        return zipPostal;
    }

    public void setZipPostal(String zipPostal) {
        this.zipPostal = zipPostal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                "lastUpdated=" + getLastModifiedDate() +
                ", lastUpdateUser='" + getLastModifiedBy() + '\'' +
                ", created=" + getCreatedDate() +
                ", createUser='" + getCreatedBy() + '\'' +
                '}';
    }
}
