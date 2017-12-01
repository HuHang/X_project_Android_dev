package com.cztek.concept.cargps.http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.cztek.concept.cargps.constants.GlobalVariables;
import com.tamic.novate.Novate;
import com.tamic.novate.callback.ResponseCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by charlot
 * Date: 2017/11/30.
 */

public class HttpClient {
    @SuppressLint("StaticFieldLeak")
    private static Novate mNovate = null;

    public static void init(Context context, boolean reInit) {
        if (reInit){
            mNovate = null;
        }
        if (mNovate == null) {
            Context appliactionContext;
            try {
                Activity activity = (Activity) context;
                appliactionContext = activity.getApplicationContext();
            } catch (Exception e) {
                appliactionContext = context;
            }

            OkHttpClient.Builder builder = new OkHttpClient.Builder();


//            SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.basic_auth, Arrays.toString(EncodeUtils.base64Encode(key_value)));
//Basic U3lzdGVtOjEyMzQ1NkA=
//            final String bastring = Credentials.basic("System","123456@");

            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("AppType", "TPOS")
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            builder.interceptors().clear();
            builder.addInterceptor(headerInterceptor);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);
//            builder.retryOnConnectionFailure(true);

            OkHttpClient okHttpClient = builder.build();

            String baseUrl = SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverURL) + ApiStore.apiVersion;

            String key_value = "System:123456@";
            final String basicString = "Basic " + EncodeUtils.base64Encode2String(key_value.getBytes());
            Map<String,String> header = new HashMap<>();
            header.put("Authorization",basicString);

            mNovate = new Novate.Builder(appliactionContext)
                    .addHeader(header)
                    .addCache(false)
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }

    public static Novate novateClient() {
        if (mNovate == null) {
            Log.e("Novate", "Novate is null!");
        }
        return mNovate;
    }

    public static <T> void  get(String url, ResponseCallback<T, ResponseBody> callBack){
        ApiService apiService = mNovate.create(ApiService.class);
        mNovate.call(apiService.executeGet(url),callBack);
    }

    public static <T> void get(String url, Map<String,Object> params, HttpCallback<T> httpCallback){
        mNovate.rxGet(url,params,httpCallback);
    }

    public static <T> void post(String url,Map<String,Object> params,HttpCallback<T> httpCallback){
        mNovate.rxBody(url,params,httpCallback);

    }

}
