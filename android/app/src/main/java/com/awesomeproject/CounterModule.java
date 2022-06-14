package com.awesomeproject;

import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import android.content.Intent;
import android.net.Uri;

public class CounterModule extends ReactContextBaseJavaModule {

    private int count = 0;

    public CounterModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override
    public String getName() {
        return "Counter";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("initialCount", 0);
        return constants;
    }

    @ReactMethod
    public void getCount(Callback callback) {
     

        callback.invoke(count);
    }

    @ReactMethod
    public void increment(String number) {
       
        // try {
        //     count = count + 1;
        //     Log.d("DEBUG", String.format("---------- count is (%s)", count));
        //     promise.resolve(count);

        //     WritableMap params = Arguments.createMap();
        //     params.putInt("count", count);
        //     sendEvent(this.getReactApplicationContext(), "onIncrement", params);
        // } catch (IllegalViewOperationException e) {
        //     promise.reject("VIEW_ERROR", e.getMessage());
        // }
         Activity activity = getCurrentActivity();
        if (activity != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            activity.startActivity(intent);
        }

        
// Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
// "tel", "12131313", null));
// startActivity(phoneIntent);
    }

    @ReactMethod
    public void decrement(Promise promise) {
        try {
            if (count == 0) {
              promise.reject("E_COUNT", "count count cannot be negative.");
            } else {
                count = count - 1;
                Log.d("DEBUG", String.format("---------- count is (%s)", count));
                promise.resolve(count);

                WritableMap params = Arguments.createMap();
                params.putInt("count", count);
                sendEvent(this.getReactApplicationContext(), "onDecrement", params);
            }
        } catch (IllegalViewOperationException e) {
            promise.reject("VIEW_ERROR", e.getMessage());
        }
    }

    private void sendEvent(
            ReactContext reactContext,
            String eventName,
            @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
