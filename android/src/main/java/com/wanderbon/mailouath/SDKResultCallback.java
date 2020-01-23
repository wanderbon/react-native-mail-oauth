package com.wanderbon.mailouath;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.MailRuCallback;

class SDKResultCallback implements MailRuCallback {
    private Promise resultPromise;

    public SDKResultCallback(Promise promise) {
        this.resultPromise = promise;
    }

    @Override
    public void onResult(Object o) {
        this.resultPromise.resolve(o);
    }

    @Override
    public void onError(Object o) {
        this.resultPromise.resolve(o);
    }
}