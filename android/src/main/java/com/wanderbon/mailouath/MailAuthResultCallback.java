package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.MailRuCallback;

class MailAuthResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public MailAuthResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object objectResult) {
        AuthResult authResult = (AuthResult) objectResult;

        MailRuAuthSdk.getInstance().requestOAuthTokens(authResult, new MailTokensCallback(this.resultPromise, authResult));
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}