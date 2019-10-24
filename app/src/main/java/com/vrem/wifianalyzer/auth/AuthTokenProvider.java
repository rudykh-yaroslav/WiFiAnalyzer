package com.vrem.wifianalyzer.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.vrem.wifianalyzer.MainContext;

public class AuthTokenProvider {
    private static final String APP_PREFERENCES = "settings";
    private static final String AUTH_KEY = "authKey";

    public String getAuthKey() {
        return getSharedPreferences().getString(AUTH_KEY, "");
    }

    public void setAuthKey(String authKey) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(APP_PREFERENCES, authKey);
        editor.commit();
    }

    public void resetAuthKey() {
        setAuthKey("");
    }

    private SharedPreferences getSharedPreferences() {
        return MainContext.INSTANCE.getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
}
