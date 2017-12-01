package com.cztek.concept.cargps.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by HH
 * Date: 2017/12/1
 */

public abstract class HttpListCallback<T> extends RxStringCallback {


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

    public abstract void OnSuccess(ArrayList<T> response);

    public abstract void OnFailure(String message);

    public abstract void OnRequestStart();

    public abstract void OnRequestFinish();


    private ArrayList<T> transform(String response) {
        ArrayList<T> mList = new ArrayList<>();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Gson gson = new Gson();
        try {
            if (response.charAt(0) == '[') {
                JsonArray array = new JsonParser().parse(response).getAsJsonArray();
                for (final JsonElement elem : array) {
                    mList.add(gson.fromJson(elem, entityClass));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;

    }
}
