package ua.org.gostroy.communityJavaProject.camel;

import org.apache.camel.CamelContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Panov Sergey on 10/6/2014.
 */
public class SpringXmlSyntaxExmpl1 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/camel.xml");
        CamelContext camelContext = (CamelContext)context.getBean("camelContext");
        camelContext.start();

        Thread.sleep(3000);
        camelContext.stop();
        context.close();
    }
}
