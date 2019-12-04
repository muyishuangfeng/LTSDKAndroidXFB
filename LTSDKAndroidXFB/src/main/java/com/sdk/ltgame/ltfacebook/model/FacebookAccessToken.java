package com.sdk.ltgame.ltfacebook.model;


import com.sdk.ltgame.core.common.Target;
import com.sdk.ltgame.core.model.token.AccessToken;

public class FacebookAccessToken extends AccessToken {


    private String access_token;

    @Override
    public int getLoginTarget() {
        return Target.LOGIN_FACEBOOK;
    }

    @Override
    public String getAccess_token() {
        return access_token;
    }

    @Override
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
