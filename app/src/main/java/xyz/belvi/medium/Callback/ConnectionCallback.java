package xyz.belvi.medium.Callback;

import xyz.belvi.medium.MediumObject.MediumError;

/**
 * Created by zone2 on 6/10/16.
 */
public interface ConnectionCallback {
    abstract void connectionFailed(MediumError mediumError);
}
