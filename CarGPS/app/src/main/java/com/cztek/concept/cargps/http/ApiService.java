package com.cztek.concept.cargps.http;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by charlot
 * Date: 2017/11/30.
 */

public interface ApiService {
    @GET
    Observable<ResponseBody> executeGet(@Url String url);
}
