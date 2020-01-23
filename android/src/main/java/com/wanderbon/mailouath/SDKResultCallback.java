package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;
    private WritableMap result;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object o) {
        AuthResult authResult = (AuthResult) o;
        Log.i("ON_RESULT_RESULT", authResult.getCodeVerifier());
        Log.i("ON_RESULT_RESULT", authResult.getAuthCode());

        result.putString("codeVerifier", authResult.getCodeVerifier());
        result.putString("authCode", authResult.getAuthCode());
        this.resultPromise.resolve(result);
        result = null;
    }

    @Override
    public void onError(Object o) {
        this.resultPromise.reject((Throwable) o);
    }
}