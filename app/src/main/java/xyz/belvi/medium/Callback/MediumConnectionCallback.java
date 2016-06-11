package xyz.belvi.medium.Callback;


import android.os.Bundle;

import xyz.belvi.medium.MediumObject.OauthDetails;

/**
 * Created by zone2 on 6/10/16.
 */
public interface MediumConnectionCallback extends ConnectionCallback {
    void onAccessTokenRetrieved(OauthDetails oauthDetails);

    void onCodeRetrieved(Bundle bundle);

    void onAccessTokenRefreshed(OauthDetails oauthDetails);

    void onAccessDenied();
}
