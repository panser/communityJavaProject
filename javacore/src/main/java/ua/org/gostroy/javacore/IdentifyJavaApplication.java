package ua.org.gostroy.javacore;

import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by s.panov on 9/30/2015.
 */
public class IdentifyJavaApplication {

    public static void main(String[] args) {

        String name1 = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name1);

        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement) jvm.get(runtime);
            Method pid_method = mgmt.getClass().getDeclaredMethod("getProcessId");
            pid_method.setAccessible(true);

            int pid = (Integer) pid_method.invoke(mgmt);
            System.out.println(pid);
        }catch (NoSuchFieldException|NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
