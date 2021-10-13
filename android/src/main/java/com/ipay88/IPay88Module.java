package com.ipay88;


import android.content.Intent;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.ipay.IPayIH;
import com.ipay.IPayIHPayment;
import com.ipay.IPayIHResultDelegate;

import java.io.Serializable;

/**
 * Created by yussuf on 2/28/18.
 * Forked by Yik Kok on 31/3/2020
 * Forked by VirtualSpirit & Modify By me@rahmatzulfikri.xyz on 20/07/2021
 */

public class IPay88Module extends ReactContextBaseJavaModule {
    private static  ReactApplicationContext context;

    public IPay88Module(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "IPay88";
    }

    @ReactMethod
    public void pay(ReadableMap data) {
        context = getReactApplicationContext();

        // Precreate payment
        try {
            IPayIHPayment payment = new IPayIHPayment();
            payment.setMerchantCode (data.getString("merchantCode"));
            payment.setPaymentId (data.getString("paymentId"));
            payment.setCurrency (data.getString("currency"));
            payment.setRefNo (data.getString("referenceNo"));
            payment.setAmount (data.getString("amount"));
            payment.setProdDesc (data.getString("productDescription"));
            payment.setUserName (data.getString("userName"));
            payment.setUserEmail (data.getString("userEmail"));
            payment.setRemark (data.getString("remark"));
            payment.setLang (data.getString("utfLang"));
            payment.setCountry (data.getString("country"));
            payment.setBackendPostURL (data.getString("backendUrl"));

            Intent checkoutIntent = IPayIH.getInstance().checkout(payment, getReactApplicationContext(), new IPay88ResultDelegate(context), IPayIH.PAY_METHOD_CREDIT_CARD);
            checkoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(checkoutIntent);
        } catch (Exception e) {
            WritableMap params = Arguments.createMap();
            params.putString("referenceNo", data.getString("referenceNo"));
            params.putString("amount", data.getString("amount"));
            params.putString("remark", data.getString("remark"));
            params.putString("error", e.getMessage());
            sendEvent(context, "ipay88:failed", params);
        }
    }


    static void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

}