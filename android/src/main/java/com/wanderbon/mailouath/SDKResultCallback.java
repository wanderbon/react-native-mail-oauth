package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object o) {
        AuthResult authResult = (AuthResult) o;
        Log.i("ON_RESULT_RESULT", authResult.getCodeVerifier());
        Log.i("ON_RESULT_RESULT", authResult.getAuthCode());
//        this.resultPromise.resolve(o);
    }

    @Override
    public void onError(Object o) {
        Log.i("ON_RESULT_ERROR", o + "");
//        this.resultPromise.resolve(o);
    }
}