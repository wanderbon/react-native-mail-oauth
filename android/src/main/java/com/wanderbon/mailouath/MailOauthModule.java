package com.wanderbon.mailouath;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.MainThread;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.queue.MessageQueueThreadImpl;

import ru.mail.auth.sdk.MailRuAuthSdk;

public class MailOauthModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private ReactApplicationContext reactContext;
    private Promise resultPromise;
    private MessageQueueThreadImpl nativeModulesThread;

    public MailOauthModule(ReactApplicationContext reactContext) {
        super(reactContext);
        nativeModulesThread = (MessageQueueThreadImpl) reactContext.getCatalystInstance().getReactQueueConfiguration().getNativeModulesQueueThread();
        reactContext.addActivityEventListener(this);
        this.reactContext = reactContext;
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

    @MainThread@ReactMethod
    public void init() {
        dispatchInAppropriateThread(new Runnable() {
            @Override
            public void run() {
                MailRuAuthSdk.initialize(reactContext);
            }
        });
    }

    @ReactMethod
    public void logIn(final Promise promise) {
        this.resultPromise = promise;
        MailRuAuthSdk.getInstance().startLogin(this.getCurrentActivity());
    }

    protected void dispatchInAppropriateThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }

        if (nativeModulesThread.getLooper().getThread().isAlive()) {
            this.reactContext.runOnNativeModulesQueueThread(runnable);
        } else {
            this.reactContext.runOnUiQueueThread(runnable);
        }
    }
}