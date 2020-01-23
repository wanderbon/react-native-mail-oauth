package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.MailRuCallback;
import ru.mail.auth.sdk.api.token.OAuthTokensResult;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object objectResult) {
        Log.i("USER_INFO", objectResult + "");
        OAuthTokensResult oAuthTokensResult = (OAuthTokensResult) objectResult;

        MailRuAuthSdk.getInstance().requestUserInfo(oAuthTokensResult, new SDKUserInfoCallBack(resultPromise, oAuthTokensResult));
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}