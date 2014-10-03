package ua.org.gostroy.communityJavaProject.core_spring_data.service;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.org.gostroy.communityJavaProject.core_spring_data.entity.AddressForAudit;

/**
 * Created by Panov Sergey on 10/3/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ua/org/gostroy/communityJavaProject/core_spring_data/applicationContext.xml")
//@TransactionConfiguration
//@Transactional
public class AddressForAuditServiceTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    AddressForAudit testAddressForAudit;

    @Autowired
    AddressService addressService;

    @Before
    public void setup() {
        testAddressForAudit = new AddressForAudit();
        testAddressForAudit.setAddress("save");
        LOG.trace(getClass() + ": before save, testAddress = " + testAddressForAudit);
        testAddressForAudit = addressService.save(testAddressForAudit);
        LOG.trace(getClass() + ": after save, testAddress = " + testAddressForAudit);
        Assert.assertNotNull(testAddressForAudit.getCreatedBy());
        Assert.assertNotNull(testAddressForAudit.getCreatedDate());
        Assert.assertNotNull(testAddressForAudit.getLastModifiedBy());
        Assert.assertNotNull(testAddressForAudit.getLastModifiedDate());
    }

    @After
    public void destroy() {
        addressService.delete(testAddressForAudit);
    }

    @Test
    public void springDataAuditingTest(){
        LOG.trace(getClass() + " : springDataAuditingTest ... ");

        DateTime lastModifiedDate = testAddressForAudit.getLastModifiedDate();

        testAddressForAudit.setAddress("update1");
        LOG.trace(getClass() + ": before update, testAddress = " + testAddressForAudit);
        testAddressForAudit = addressService.update(testAddressForAudit);
        LOG.trace(getClass() + ": after update, testAddress = " + testAddressForAudit);

        Assert.assertNotEquals(testAddressForAudit.getLastModifiedDate(), lastModifiedDate);

        LOG.trace(getClass() + " : springDataAuditingTest.");
    }
}
