package xyz.belvi.medium.MediumObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import xyz.belvi.medium.Enums.EnumUtils;
import xyz.belvi.medium.Enums.Scope;

/**
 * Created by zone2 on 6/11/16.
 */
public class OauthDetails {
    String tokenType = "", accessToken = "", refreshToken = "";
    long expiringTimeStamp;
    ArrayList<Scope> scopes;

    public OauthDetails() {
    }

    public OauthDetails(String oauthObj) {
        try {
            JSONObject oauthObject = new JSONObject(oauthObj);
            setAccessToken(oauthObject.optString("access_token"));
            setTokenType(oauthObject.optString("token_type"));
            setRefreshToken(oauthObject.optString("refresh_token"));
            setExpiringTimeStamp(oauthObject.optLong("expires_at"));
            JSONArray scopes = oauthObject.getJSONArray("scope");
            ArrayList<Scope> scopeArrayList = new ArrayList<>();
            for (int index = 0; index < scopes.length(); index++) {
                scopeArrayList.add(EnumUtils.getScopeByName(scopes.getString(index)));
            }
            setScopes(scopeArrayList);
            setExpiringTimeStamp(oauthObject.optLong("expires_at"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiringTimeStamp() {
        return this.expiringTimeStamp;
    }

    public void setExpiringTimeStamp(long timeStamp) {
        this.expiringTimeStamp = timeStamp;
    }

    public ArrayList<Scope> getScopes() {
        return this.scopes;
    }

    public void setScopes(ArrayList<Scope> scopes) {
        this.scopes = scopes;
    }
}
