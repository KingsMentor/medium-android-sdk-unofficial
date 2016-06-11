package xyz.belvi.medium.Callback;

import xyz.belvi.medium.MediumObject.MediumUser;

/**
 * Created by zone2 on 6/10/16.
 */
public interface MediumUserAuthCallback extends ConnectionCallback {
    void onUserDetailsRetrieved(MediumUser mediumUser);
}
