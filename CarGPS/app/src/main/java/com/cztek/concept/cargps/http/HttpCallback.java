package com.cztek.concept.cargps.http;

import com.google.gson.Gson;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.lang.reflect.ParameterizedType;

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
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try {
            if (response.charAt(0) == '{'){
                dataResponse = new Gson().fromJson(response, entityClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataResponse;

    }

}
