package xyz.belvi.medium.Test;

import android.content.Context;

/**
 * Created by zone2 on 6/11/16.
 */
public class AppPreference {

    public static final String CODE = "xyz.belvi.medium.Test.code";
    public static final String ACCESS_TOKEN = "xyz.belvi.medium.Test.ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "xyz.belvi.medium.Test.REFRESH_TOKEN";
    public static final String EXPIRES_AT = "xyz.belvi.medium.Test.EXPIRES_AT";
    public static final String TOKEN_TYPE = "xyz.belvi.medium.Test.TOKEN_TYPE";
    public static final String USER_ID = "xyz.belvi.medium.Test.USER_ID";

    public static void saveValue(Context context, String key, String obj) {
        context.getSharedPreferences(AppPreference.class.getName(), Context.MODE_PRIVATE).edit().putString(AppPreference.class.getName() + "." + key, obj).commit();
    }

    public static String getValue(Context context, String key) {
        return context.getSharedPreferences(AppPreference.class.getName(), Context.MODE_PRIVATE).getString(AppPreference.class.getName() + "." + key, "");
    }
}
