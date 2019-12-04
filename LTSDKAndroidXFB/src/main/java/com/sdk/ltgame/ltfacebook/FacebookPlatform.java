package com.sdk.ltgame.ltfacebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sdk.ltgame.core.common.LTGameOptions;
import com.sdk.ltgame.core.common.LTGameSdk;
import com.sdk.ltgame.core.common.Target;
import com.sdk.ltgame.core.impl.OnLoginStateListener;
import com.sdk.ltgame.core.impl.OnRechargeListener;
import com.sdk.ltgame.core.model.LoginObject;
import com.sdk.ltgame.core.model.RechargeObject;
import com.sdk.ltgame.core.platform.AbsPlatform;
import com.sdk.ltgame.core.platform.IPlatform;
import com.sdk.ltgame.core.platform.PlatformFactory;
import com.sdk.ltgame.core.uikit.BaseActionActivity;
import com.sdk.ltgame.core.util.LTGameUtil;
import com.sdk.ltgame.ltfacebook.uikit.FacebookActionActivity;

public class FacebookPlatform extends AbsPlatform {

    private static final String TAG = FacebookPlatform.class.getSimpleName();
    private FacebookLoginHelper mLoginHelper;


    private FacebookPlatform(Context context, boolean isServerTest, String appId, String appKey,
                             String adID, String packageID, int target) {
        super(context, isServerTest, appId, appKey, adID, packageID, target);
    }


    public static class Factory implements PlatformFactory {

        @Override
        public IPlatform create(Context context, int target) {
            IPlatform platform = null;
            LTGameOptions options = LTGameSdk.options();
            if (!LTGameUtil.isAnyEmpty(options.getLtAppId(),
                    options.getLtAppKey(), options.getAdID(), options.getPackageID())) {
                platform = new FacebookPlatform(context, options.getISServerTest(), options.getLtAppId(),
                        options.getLtAppKey(), options.getAdID(), options.getPackageID(),
                        target);
            }
            return platform;
        }

        @Override
        public int getPlatformTarget() {
            return Target.PLATFORM_FACEBOOK;
        }

        @Override
        public boolean checkLoginPlatformTarget(int target) {
            return target == Target.LOGIN_FACEBOOK;
        }

        @Override
        public boolean checkRechargePlatformTarget(int target) {
            return false;
        }
    }

    @Override
    public Class getUIKitClazz() {
        return FacebookActionActivity.class;
    }

    @Override
    public void onActivityResult(BaseActionActivity activity, int requestCode, int resultCode, Intent data) {
        super.onActivityResult(activity, requestCode, resultCode, data);
        mLoginHelper.setOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void login(Activity activity, int target, LoginObject object, OnLoginStateListener listener) {
        mLoginHelper = new FacebookLoginHelper(activity, listener, target);
        mLoginHelper.login(object.getFacebookAppID(), activity, object.isLoginOut());

    }

    @Override
    public void recharge(Activity activity, int target, RechargeObject object, OnRechargeListener listener) {

    }
}
