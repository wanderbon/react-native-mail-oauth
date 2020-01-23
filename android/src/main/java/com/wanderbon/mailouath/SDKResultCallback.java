package com.wanderbon.mailouath;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object objectResult) {
        AuthResult authResult = (AuthResult) objectResult;

        MailRuAuthSdk.getInstance().requestOAuthTokens(authResult, new SDKUserInfoCallBack(resultPromise, authResult));
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}