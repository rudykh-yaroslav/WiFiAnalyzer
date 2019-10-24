package com.vrem.wifianalyzer.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.vrem.wifianalyzer.MainContext;

public class AuthTokenProvider {
    private static final String APP_PREFERENCES = "settings";
    private static final String AUTH_KEY = "authKey";
    private static final String LOGIN = "login";

    public String getAuthKey() {
        return getSharedPreferences().getString(AUTH_KEY, "");
    }

    public String getLogin() {
        return getSharedPreferences().getString(LOGIN, "");
    }

    public void setUserData(@Nullable UserData userData) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(AUTH_KEY, userData == null ? "" : userData.getToken());
        editor.putString(LOGIN, userData == null ? "" : userData.getLogin());
        editor.commit();
    }

    public void resetUserData() {
        setUserData(null);
    }

    private SharedPreferences getSharedPreferences() {
        return MainContext.INSTANCE.getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
}
