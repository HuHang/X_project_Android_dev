package com.cztek.concept.cargps.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlot
 * Date: 2017/11/30.
 */

public abstract class HttpCallback<T> extends RxStringCallback {
    @Override
    public void onStart(Object tag) {
        super.onStart(tag);
        OnRequestStart();
    }

    @Override
    public void onCompleted(Object tag) {
        super.onCompleted(tag);
        OnRequestFinish();
    }

    @Override
    public void onNext(Object tag, String response) {
        OnSuccess(transform(response));
    }

    @Override
    public void onError(Object tag, Throwable e) {
        OnFailure(e.getMessage());
    }

    @Override
    public void onCancel(Object tag, Throwable e) {
        OnFailure(e.getMessage());
    }

    public abstract void OnSuccess(T response);

    public abstract void OnFailure(String message);

    public abstract void OnRequestStart();

    public abstract void OnRequestFinish();


    private T transform(String response) {
        T dataResponse = null;

        try {
            Type collectionType = new TypeToken<T>() {}.getType();

            String dataStr = null;
            if (response.charAt(0) == '{') {

            } else if (response.charAt(0) == '[') {
                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();


            }
            dataResponse = new Gson().fromJson(dataStr, collectionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataResponse;

    }

}
