package xyz.belvi.medium.MediumObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import xyz.belvi.medium.Enums.Role;

/**
 * Created by zone2 on 6/10/16.
 */
public class Contributor {
    String userId, publicationId;
    Role role;

    public Contributor() {

    }

    public ArrayList<Contributor> buildContributor(String response) {
        ArrayList<Contributor> contributors = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray dataArray = responseObject.getJSONArray("data");
            for (int index = 0; index < dataArray.length(); index++) {
                contributors.add(new Contributor(dataArray.getJSONObject(index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contributors;
    }

    public Contributor(JSONObject contributorObject) {
        setUserId(contributorObject.optString("userId"));
        setPublicationId(contributorObject.optString("publicationId"));
        setRole(Role.valueOf(contributorObject.optString("userId").toUpperCase()));
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicationId() {
        return this.publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
