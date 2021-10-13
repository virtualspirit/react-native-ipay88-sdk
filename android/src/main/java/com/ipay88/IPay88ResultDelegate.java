package com.ipay88;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.ipay.IPayIHResultDelegate;

import java.io.Serializable;

public class IPay88ResultDelegate implements IPayIHResultDelegate, Serializable {
    private static ReactApplicationContext context;
    public IPay88ResultDelegate(ReactApplicationContext reactContext) {
        context = reactContext;
    }

    @Override
    public void onConnectionError(String s, String s1, String s2, String s3, String s4, String s5) {

    }

    @Override
    public void onPaymentSucceeded(String trasId, String refNo, String amount, String remark, String authCode) {
        WritableMap params = Arguments.createMap();
        params.putString("transactionId", trasId);
        params.putString("referenceNo", refNo);
        params.putString("amount", amount);
        params.putString("remark", remark);
        params.putString("authorizationCode", authCode);
        sendEvent(context, "ipay88:success", params);
    }

    @Override
    public void onPaymentFailed(String transId, String refNo, String amount, String remark, String errDesc) {
        WritableMap params = Arguments.createMap();
        params.putString("transactionId", transId);
        params.putString("referenceNo", refNo);
        params.putString("amount", amount);
        params.putString("remark", remark);
        params.putString("error", errDesc);
        sendEvent(context, "ipay88:failed", params);
    }

    @Override
    public void onPaymentCanceled(String transId, String refNo, String amount, String remark, String errDesc) {
        WritableMap params = Arguments.createMap();
        params.putString("transactionId", transId);
        params.putString("referenceNo", refNo);
        params.putString("amount", amount);
        params.putString("remark", remark);
        params.putString("error", errDesc);
        sendEvent(context, "ipay88:canceled", params);
    }

    @Override
    public void onRequeryResult(String s, String s1, String s2, String s3) {

    }

    static void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }
}
