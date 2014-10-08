package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.util.List;

/**
 * Created by Panov Sergey on 10/7/2014.
 */
public class UserDataGridJSONResponseBuilder extends AbstractDataGridJSONResponseBuilder<User> {
    @Override
    protected void fillRows(List<User> data, JSONArray rows) throws JSONException {
        for (User user : data) {
            JSONObject userJson = new JSONObject();
            userJson.put("login", user.getLogin());
            userJson.put("email", user.getEmail());
            userJson.put("password", user.getPassword());
            rows.put(userJson);
        }
    }
}
