package xyz.belvi.medium.Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import xyz.belvi.medium.Callback.MediumConnectionCallback;
import xyz.belvi.medium.Callback.MediumUserAuthCallback;
import xyz.belvi.medium.ClientOperations.ClientConstant;
import xyz.belvi.medium.ClientOperations.MediumClient;
import xyz.belvi.medium.Enums.ApiHost;
import xyz.belvi.medium.Enums.EnumUtils;
import xyz.belvi.medium.Enums.ErrorCodes;
import xyz.belvi.medium.Enums.Scope;
import xyz.belvi.medium.MediumObject.MediumError;
import xyz.belvi.medium.MediumObject.MediumUser;
import xyz.belvi.medium.MediumObject.OauthDetails;

/**
 * Created by zone2 on 6/10/16.
 */
public class ApiTest extends AppCompatActivity implements MediumConnectionCallback, MediumUserAuthCallback {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String code = AppPreference.getValue(this, AppPreference.CODE);
        String refresh_token = AppPreference.getValue(this, AppPreference.REFRESH_TOKEN);
        String tokenType = AppPreference.getValue(this, AppPreference.TOKEN_TYPE);
        String accessToken = AppPreference.getValue(this, AppPreference.ACCESS_TOKEN);
        try {
//            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.REFRESH_TOKEN)
//                    .code(code)
//                    .clientSecret("32e426452c95528a27bfb0b88d93d2767c45d2f1")
//                    .refreshToken(refresh_token)
//                    .redirectUri(null)
//                    .addConnectionCallback(this)
//                    .clientID("347a306d2419").build();

            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.ME)
                    .code(code)
                    .clientSecret("32e426452c95528a27bfb0b88d93d2767c45d2f1")
                    .tokenType(tokenType)
                    .accessToken(accessToken)
                    .addConnectionCallback(this)
                    .clientID("347a306d2419").build();

            mediumClient.connect();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
        if (error == ErrorCodes.AUTH_CODE_EXPIRED || error == ErrorCodes.NO_CODE_SPECIFIED) {
            MediumClient mediumClient = null;
            try {
                mediumClient = new MediumClient.Builder(this, ApiHost.REQUEST_CODE)
                        .state("nigeria")
                        .redirectUri(null)
                        .addScope(Scope.BASIC)
                        .addScope(Scope.POST)
                        .addScope(Scope.PUBLICATION)
                        .addConnectionCallback(this)
                        .clientID("347a306d2419").build();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mediumClient.connect();
        }

    }

    @Override
    public void onUserDetailsRetrieved(MediumUser mediumUser) {
        AppPreference.saveValue(this, AppPreference.USER_ID, mediumUser.getId());
    }
}
