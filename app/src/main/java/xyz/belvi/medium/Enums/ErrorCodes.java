package xyz.belvi.medium.Enums;

/**
 * Created by zone2 on 6/11/16.
 */
public enum ErrorCodes {

    ACCESS_TOKEN_REQUIRED(6000),
    AUTH_CODE_EXPIRED(6017),
    NO_CODE_SPECIFIED(6013),
    INVALID_USER_ID(6026),
    CONNECTION_FAILED(7000), UNKNOWN(7001);

    private int errorCode;

    ErrorCodes(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
