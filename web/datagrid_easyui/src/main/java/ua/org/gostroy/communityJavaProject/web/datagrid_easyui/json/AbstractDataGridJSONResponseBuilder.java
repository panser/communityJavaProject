package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Panov Sergey on 10/7/2014.
 */
public abstract class AbstractDataGridJSONResponseBuilder<T> implements JSONResponseBuilder<List<T>> {
    @Override
    public JSONObject build(List<T> data) throws JSONException {
        return build(data, -1);
    }

    @Override
    public JSONObject build(List<T> data, int total) throws JSONException {
        JSONObject response = new JSONObject();
        JSONArray rows = new JSONArray();
        fillRows(data, rows);
        response.put("total", (total >= 0) ? total : data.size());
        response.put("rows", rows);
        return response;
    }

    abstract protected  void fillRows(List<T> data, JSONArray rows) throws JSONException;
}
