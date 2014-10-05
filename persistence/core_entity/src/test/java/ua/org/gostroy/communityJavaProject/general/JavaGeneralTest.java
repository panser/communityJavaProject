package ua.org.gostroy.communityJavaProject.general;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
public class JavaGeneralTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Test
    public void testModOperator(){
        int numberPerPage = 10;
        int numberOfResults = 13;
        int dividedBy = numberOfResults/numberPerPage;

        int numberOfPages = 1 + dividedBy;

        assertTrue("Two pages ", 2 == numberOfPages);
    }

    @Test
    public void testGetUrlQuery() throws MalformedURLException {
        URL url = new URL("http://www.someurl.com?someparam1=10&someparam2=10&currentPage=1");
        String query = url.getQuery();
        Assert.assertTrue(query.equals("someparam1=10&someparam2=10&currentPage=1"));
        query = query.substring(0, query.lastIndexOf("&currentPage="));
        Assert.assertTrue(query.equals("someparam1=10&someparam2=10"));
    }

    @Test
    public void testIntegerRounding(){
        Float f = new Float(20.6);

        LOG.debug("rounding up?  " + f.intValue());
        Assert.assertTrue("should be rounded to 21", Math.round(f) == 21);

        f = new Float(19.49f);
        Assert.assertTrue("should be rounded to 19", Math.round(f) == 19);
        LOG.debug("rounding down? " + f.intValue());
    }


}
