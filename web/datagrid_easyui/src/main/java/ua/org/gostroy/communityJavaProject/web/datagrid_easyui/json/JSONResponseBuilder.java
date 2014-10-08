package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Panov Sergey on 10/7/2014.
 */
public interface JSONResponseBuilder<T> {
    /*
    * Default method for building JSON.
    * */
    public JSONObject build(T data) throws JSONException;

    /*
    * Advanced method for building JSON.
    * */
    public JSONObject build(T data, int total) throws JSONException;
}
