package xyz.belvi.medium.MediumObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zone2 on 6/10/16.
 */
public class MediumError {
    int errorCode;
    String errorMessage;
//    medium error format
//    {"errors":[{"message":"Authorization code has expired.","code":6017}]}

    public MediumError(String message, int code) {
        setErrorMessage(message);
        setErrorCode(code);
    }

    public MediumError(String errorString) {
        try {
            JSONObject errorObject = new JSONObject(errorString);
            JSONArray errorArray = errorObject.getJSONArray("errors");
            JSONObject errorDetailsObject = errorArray.getJSONObject(0);
            setErrorCode(errorDetailsObject.getInt("code"));
            setErrorMessage(errorDetailsObject.getString("message"));
        } catch (JSONException e) {
        }

    }

    public int getErrorCode() {
        return this.errorCode;
    }

    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
