package xyz.belvi.medium.MediumObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zone2 on 6/10/16.
 */
public class MediumImage {
    String url,md5;

    public MediumImage(){

    }

    public MediumImage(String responseString){
        try {
            JSONObject responseObject = new JSONObject(responseString);
            JSONObject dataObject = responseObject.getJSONObject("data");
            setUrl(dataObject.optString("url"));
            setMd5(dataObject.optString("md5"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
