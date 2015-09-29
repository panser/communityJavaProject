package ua.org.gostroy.communityJavaProject.rmi.cxf.util;

import org.joda.time.DateTime;

/**
 * Created by Sergey on 9/6/2015.
 */
public class DateTimeAdapter {

    public static DateTime unmarshal(String v){
        return new DateTime(v);
    }

    public static String marshal(DateTime v){
        return v.toString();
    }
}
