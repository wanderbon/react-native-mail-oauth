package com.wanderbon.mailouath;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.MainThread;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.queue.MessageQueueThreadImpl;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import ru.mail.auth.sdk.MailRuAuthSdk;

public class MailOauthModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private ReactApplicationContext reactContext;
    private Promise resultPromise;
    private MessageQueueThreadImpl nativeModulesThread;
    private Handler mainHandler;

    public MailOauthModule(final ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);

        this.reactContext = reactContext;

        if(this.getCurrentActivity() != null) {
            this.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MailRuAuthSdk.initialize(reactContext);
                }
            });
        }
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (!MailRuAuthSdk.getInstance().handleActivityResult(requestCode, resultCode, data, new SDKResultCallback(this.resultPromise))) {
            this.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!MailRuAuthSdk.getInstance().handleActivityResult(requestCode, resultCode, data, new SDKResultCallback(this.resultPromise))) {
            this.onActivityResult(requestCode, resultCode, data);
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
        MailRuAuthSdk.getInstance().startLogin(this.getCurrentActivity());
    }
}