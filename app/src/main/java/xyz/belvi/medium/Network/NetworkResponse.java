package xyz.belvi.medium.Network;

/**
 * Created by zone2 on 1/30/16.
 */
public class NetworkResponse {

    String responseString;
    int statusCode;
    boolean isSuccess;


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
