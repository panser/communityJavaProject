package ua.org.gostroy.communityJavaProject.general;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;


/**
 * Created by Panov Sergey on 9/29/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ua/org/gostroy/communityJavaProject/core_entity/applicationContext.xml")
public class SpringContextTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void testSpringContext(){
        Assert.assertNotNull(dataSource);
    }

}