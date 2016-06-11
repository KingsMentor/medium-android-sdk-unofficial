package xyz.belvi.medium.MediumObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zone2 on 6/10/16.
 */
public class Publication {

    String id = "", description = "", name = "", url = "", imageUrl = "";

    public Publication() {

    }

    public ArrayList<Publication> buildPublication(String responseString) {
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseString);
            JSONArray dataArray = responseObject.getJSONArray("data");
            for (int index = 0; index < dataArray.length(); index++) {
                publications.add(new Publication(dataArray.getJSONObject(index)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return publications;
    }

    public Publication(JSONObject publicationObject) {
        setId(publicationObject.optString("id"));
        setDescription(publicationObject.optString("description"));
        setName(publicationObject.optString("name"));
        setUrl(publicationObject.optString("url"));
        setImageUrl(publicationObject.optString("imageUrl"));

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
