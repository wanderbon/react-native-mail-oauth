package com.wanderbon.mailouath;

import android.util.Log;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object o) {
        Log.i("ON_RESULT_RESULT", o + "");
//        this.resultPromise.resolve(o);
    }

    @Override
    public void onError(Object o) {
        Log.i("ON_RESULT_ERROR", o + "");
//        this.resultPromise.resolve(o);
    }
}