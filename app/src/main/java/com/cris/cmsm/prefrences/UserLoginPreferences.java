package com.cris.cmsm.prefrences;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cris.cmsm.models.response.LoginIfoVO;

import java.lang.reflect.Type;

public class UserLoginPreferences {
    private static final String USER_LOGIN = "USER_LOGIN";
    private static final String USER_OBJECT = "USER_OBJECT";
    private static final String IS_LOGIN = "IS_LOGIN";
    Context context;
    private Editor editor;
    private Gson gson;

    public UserLoginPreferences(Context context) {
        this.context = context;
        gson = new Gson();
        editor = context.getSharedPreferences(USER_LOGIN, Context.MODE_PRIVATE).edit();
    }

    public LoginIfoVO getLoginUser() {
        Type type = new TypeToken<LoginIfoVO>() {
        }.getType();
        String value = context.getSharedPreferences(USER_LOGIN, Context.MODE_PRIVATE).getString(USER_OBJECT, null);
        if (value != null) {
            return (LoginIfoVO) gson.fromJson(value, type);
        }
        return null;
    }

    public void setLoginUser(LoginIfoVO model) {
        if (model != null) {
            editor.putString(USER_OBJECT, gson.toJson(model));
            editor.commit();
        }
    }

    public void clearUser() {
        editor.clear();
        editor.commit();
    }

    public boolean isLogin() {
        return context.getSharedPreferences(USER_LOGIN, Context.MODE_PRIVATE).getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

}
