package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuCallback;
import ru.mail.auth.sdk.api.user.UserInfoResult;

public class SDKUserInfoCallBack implements MailRuCallback {
    private Promise resultPromise;
    private AuthResult authResult;

    public SDKUserInfoCallBack(Promise resultPromise, AuthResult authResult) {
        this.resultPromise = resultPromise;
        this.authResult = authResult;
    }

    @Override
    public void onResult(Object objectResult) {
        Log.i("USER_INFO", objectResult + "");
        UserInfoResult userInfo = (UserInfoResult) objectResult;

        Log.i("USER_INFO", userInfo.getName());

        WritableMap result = Arguments.createMap();

        result.putString("codeVerifier", authResult.getCodeVerifier());
        result.putString("authCode", authResult.getAuthCode());

        this.resultPromise.resolve(result);
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}
