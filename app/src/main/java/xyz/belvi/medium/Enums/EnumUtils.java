package xyz.belvi.medium.Enums;

import xyz.belvi.medium.MediumObject.License;

/**
 * Created by zone2 on 6/11/16.
 */
public class EnumUtils {

    public static ErrorCodes getErrorObjByCode(int code) {
        for (ErrorCodes errorCode : ErrorCodes.values()) {
            if (errorCode.getErrorCode() == code)
                return errorCode;
        }
        return ErrorCodes.UNKNOWN;
    }

    public static Scope getScopeByName(String scopeName) {
        for (Scope scope : Scope.values()) {
            if (scope.getScopeName().equalsIgnoreCase(scopeName))
                return scope;
        }
        return null;
    }

    public static License getLicenseByType(String licenceType) {
        for (License license : License.values()) {
            if (license.getLicenseType().equalsIgnoreCase(licenceType)) {
                return license;
            }
        }
        return License.ALL_RIGHT_RESERVED;
    }

    public static ImageContentType getImageByContentType(String contentType) {
        for (ImageContentType imageContentType : ImageContentType.values()) {
            if (imageContentType.getContentType().equalsIgnoreCase(contentType)) {
                return imageContentType;
            }
        }
        return ImageContentType.PNG;
    }

}
