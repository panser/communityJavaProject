package ua.org.gostroy.communityJavaProject.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by Panov Sergey on 10/6/2014.
 */
public class JavaDslSyntaxExmpl1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("{{from1.uri}}").to("{{to1.uri}}");
    }

    public static void main(String[] args) throws Exception{
        CamelContext camelContext = new DefaultCamelContext();

        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:spring/camel.properties");
        camelContext.addComponent("properties", pc);

        camelContext.addRoutes(new JavaDslSyntaxExmpl1());
        camelContext.start();

        Thread.sleep(3000);
        camelContext.stop();
    }
}
