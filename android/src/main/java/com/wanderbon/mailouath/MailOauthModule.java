package com.wanderbon.mailouath;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import ru.mail.auth.sdk.MailRuAuthSdk;

public class MailOauthModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private ReactApplicationContext reactContext;
    private Promise resultPromise;

    public MailOauthModule(final ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);

        this.reactContext = reactContext;
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        try {
            MailRuAuthSdk.getInstance().handleActivityResult(requestCode, resultCode, data, new SDKResultCallback(this.resultPromise));
        } catch (Throwable trow) {
            onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            MailRuAuthSdk.getInstance().handleActivityResult(requestCode, resultCode, data, new SDKResultCallback(this.resultPromise));
        } catch (Throwable throwable) {
            this.resultPromise.reject(throwable);
        }
    }

    public void onNewIntent(Intent intent) {

    }

    @Override
    public String getName() {
        return "MailOauth";
    }

    @ReactMethod
    public void logIn(final Promise promise) {
        this.resultPromise = promise;
        Log.i("ON_RESULT_LOGIN", this.getCurrentActivity() + "");
        MailRuAuthSdk.getInstance().startLogin(this.getCurrentActivity());
    }

    @ReactMethod
    public void init() {
        Handler uiHandler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MailRuAuthSdk.initialize(reactContext);
            }
        };

        uiHandler.post(runnable);
    }
}