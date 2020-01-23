package com.wanderbon.mailouath;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

import ru.mail.auth.sdk.AuthResult;
import ru.mail.auth.sdk.MailRuCallback;
import ru.mail.auth.sdk.api.token.OAuthTokensResult;
import ru.mail.auth.sdk.api.user.UserInfoResult;

public class MailUserInfoCallback implements MailRuCallback {
    private Promise resultPromise;
    private AuthResult authResult;
    private OAuthTokensResult oAuthTokensResult;

    public MailUserInfoCallback(Promise resultPromise, AuthResult authResult, OAuthTokensResult oAuthTokensResult) {
        this.resultPromise = resultPromise;
        this.authResult = authResult;
        this.oAuthTokensResult = oAuthTokensResult;
    }

    @Override
    public void onResult(Object objectResult) {
        UserInfoResult userInfo = (UserInfoResult) objectResult;

        WritableMap userInfoMap = Arguments.createMap();

        userInfoMap.putString("name", userInfo.getName());
        userInfoMap.putString("avatarUrl", userInfo.getAvatarUrl());
        userInfoMap.putString("email", userInfo.getEmail());
        userInfoMap.putString("mailID", userInfo.getMailID());

        WritableMap result = Arguments.createMap();

        result.putString("accessToken", oAuthTokensResult.getAccessToken());
        result.putString("authCode", authResult.getAuthCode());
        result.putString("codeVerifer", authResult.getCodeVerifier());
        result.putMap("user", userInfoMap);

        this.resultPromise.resolve(result);
    }

    @Override
    public void onError(Object objectError) {
        this.resultPromise.reject((Throwable) objectError);
    }
}
