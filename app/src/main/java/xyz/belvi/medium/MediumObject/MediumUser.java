package xyz.belvi.medium.MediumObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zone2 on 6/10/16.
 */
public class MediumUser {
    String id = "", userName = "", name = "", url = "", imageUrl = "";

    public MediumUser() {
    }

    public MediumUser(String userObj) {
        try {
            JSONObject responseObject = new JSONObject(userObj);
            JSONObject userDataObject = responseObject.getJSONObject("data");
            setId(userDataObject.optString("id"));
            setUserName(userDataObject.optString("username"));
            setName(userDataObject.optString("name"));
            setUrl(userDataObject.optString("url"));
            setImageUrl(userDataObject.optString("imageUrl"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
