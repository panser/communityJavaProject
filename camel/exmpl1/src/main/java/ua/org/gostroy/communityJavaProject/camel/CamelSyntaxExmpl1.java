package ua.org.gostroy.communityJavaProject.camel;

import org.apache.camel.spring.Main;

/**
 * Created by Panov Sergey on 10/6/2014.
 */
public class CamelSyntaxExmpl1 {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setApplicationContextUri("spring/camel.xml");
        main.start();

        Thread.sleep(3000);
        main.stop();
    }
}
