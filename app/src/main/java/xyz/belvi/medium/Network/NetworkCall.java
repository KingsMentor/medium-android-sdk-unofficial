package xyz.belvi.medium.Network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import xyz.belvi.medium.Enums.ApiHost;
import xyz.belvi.medium.MediumObject.OauthDetails;


/**
 * Created by zone2 on 1/15/16.
 */
public class NetworkCall {

    public static NetworkResponse connect(String link, ApiHost apiHost, String params, OauthDetails oauthDetails) {


        NetworkResponse networkResponse = new NetworkResponse();
        Log.e("link", link);
        try {

            URL obj = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            //add request header
            conn.setRequestMethod(apiHost.getAction());
            conn.setRequestProperty("Content-Type", apiHost.getContentType());

            if (apiHost.hasBearer()) {
                conn.setRequestProperty("Authorization", oauthDetails.getTokenType() + " " + oauthDetails.getAccessToken());
            }
            if (apiHost.getAction().trim().equalsIgnoreCase("post")) {
                // Send post request
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(params.toString());
                wr.flush();
                wr.close();
            }

            BufferedReader in;
            int status = conn.getResponseCode();

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST)
                in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
            else
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            Log.e("connector response", response.toString());
            if (callReturnsObj(response.toString())) {
                networkResponse.setIsSuccess(!isErrorObj(response.toString()));
                networkResponse.setStatusCode(conn.getResponseCode());
                networkResponse.setResponseString(response.toString());
            } else {
                initErrorMessage(networkResponse, "");
            }

            return networkResponse;

        } catch (Exception e) {
            e.printStackTrace();
            initErrorMessage(networkResponse, e.getMessage());
            return networkResponse;
        }


    }

    private static void initErrorMessage(NetworkResponse networkResponse, String message) {
        networkResponse.setStatusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
        networkResponse.setIsSuccess(false);
        networkResponse.setResponseString(message.trim().isEmpty() ? "{\"errors\":[{\"message\":\"Error occured. Check if you client was properly built.\",\"code\":501}]}" : message);
    }

    private static boolean isErrorObj(String response) {
        try {
            JSONObject responseObj = new JSONObject(response);
            return responseObj.getJSONArray("errors") != null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    private static boolean callReturnsObj(String response) {
        try {
            new JSONObject(response);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }


}


