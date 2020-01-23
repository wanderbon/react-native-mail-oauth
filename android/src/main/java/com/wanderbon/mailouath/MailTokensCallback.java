package com.wanderbon.mailouath;

import com.facebook.react.bridge.Promise;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.MailRuCallback;
import ru.mail.auth.sdk.api.token.OAuthTokensResult;

public class MailTokensCallback implements MailRuCallback {
    private Promise resultPromise;
    private AuthResult authResult;

    public MailTokensCallback(Promise resultPromise, AuthResult authResult) {
        this.resultPromise = resultPromise;
        this.authResult = authResult;
    }

    @Override
    public void onResult(Object objectResult) {
        OAuthTokensResult oAuthTokensResult = (OAuthTokensResult) objectResult;

        MailRuAuthSdk.getInstance().requestUserInfo(oAuthTokensResult, new MailUserInfoCallback(resultPromise, authResult, oAuthTokensResult));
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}
