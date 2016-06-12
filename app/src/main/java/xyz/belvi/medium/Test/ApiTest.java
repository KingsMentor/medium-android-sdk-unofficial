package xyz.belvi.medium.Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import xyz.belvi.medium.Callback.MediumConnectionCallback;
import xyz.belvi.medium.ClientOperations.ClientConstant;
import xyz.belvi.medium.ClientOperations.MediumClient;
import xyz.belvi.medium.Enums.ApiHost;
import xyz.belvi.medium.Enums.EnumUtils;
import xyz.belvi.medium.Enums.ErrorCodes;
import xyz.belvi.medium.Enums.Scope;
import xyz.belvi.medium.MediumObject.MediumError;
import xyz.belvi.medium.MediumObject.OauthDetails;
import xyz.belvi.medium.MediumObject.Post;

/**
 * Created by zone2 on 6/10/16.
 */
public class ApiTest extends AppCompatActivity implements MediumConnectionCallback {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.REQUEST_CODE)
                    .publish(new Post())
                    .addScope(Scope.BASIC)
                    .addScope(Scope.PUBLICATION)
                    .addScope(Scope.POST)
                    .redirectUri(null)
                    .state("anySate")
                    .addConnectionCallback(this)
                    .clientID("347a306d2419").build();

            mediumClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error :::: ",e.getMessage());
        }
    }

    @Override
    public void onAccessTokenRetrieved(OauthDetails oauthDetails) {
        AppPreference.saveValue(this, AppPreference.ACCESS_TOKEN, oauthDetails.getAccessToken());
        AppPreference.saveValue(this, AppPreference.REFRESH_TOKEN, oauthDetails.getRefreshToken());
        AppPreference.saveValue(this, AppPreference.EXPIRES_AT, String.valueOf(oauthDetails.getExpiringTimeStamp()));
        AppPreference.saveValue(this, AppPreference.TOKEN_TYPE, String.valueOf(oauthDetails.getTokenType()));
    }

    @Override
    public void onCodeRetrieved(Bundle bundle) {
        String code = bundle.getString(ClientConstant.connectionCode);
        AppPreference.saveValue(this, AppPreference.CODE, code);
        Toast.makeText(this, "code retrived", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAccessTokenRefreshed(OauthDetails oauthDetails) {
        AppPreference.saveValue(this, AppPreference.ACCESS_TOKEN, oauthDetails.getAccessToken());
        AppPreference.saveValue(this, AppPreference.REFRESH_TOKEN, oauthDetails.getRefreshToken());
        AppPreference.saveValue(this, AppPreference.EXPIRES_AT, String.valueOf(oauthDetails.getExpiringTimeStamp()));
        AppPreference.saveValue(this, AppPreference.TOKEN_TYPE, String.valueOf(oauthDetails.getTokenType()));
    }

    @Override
    public void onAccessDenied() {

    }

    @Override
    public void connectionFailed(MediumError mediumError) {
        Log.e("error ::: ", mediumError.getErrorMessage());
        ErrorCodes error = EnumUtils.getErrorObjByCode(mediumError.getErrorCode());


    }

}
