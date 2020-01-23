package com.wanderbon.mailouath;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object o) {
        AuthResult authResult = (AuthResult) o;

        WritableMap result = Arguments.createMap();

        result.putString("codeVerifier", authResult.getCodeVerifier());
        result.putString("authCode", authResult.getAuthCode());
        
        this.resultPromise.resolve(result);
    }

    @Override
    public void onError(Object o) {
        this.resultPromise.reject((Throwable) o);
    }
}