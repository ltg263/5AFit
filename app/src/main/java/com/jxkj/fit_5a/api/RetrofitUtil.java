package com.jxkj.fit_5a.api;


import android.util.Log;

import com.jxkj.fit_5a.conpoment.constants.ConstValues;
import com.jxkj.fit_5a.conpoment.utils.SharedUtils;
import com.jxkj.fit_5a.conpoment.utils.StringUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static RetrofitUtil util;
    private ApiService apiService;

    public static RetrofitUtil getInstance() {
        if (util == null) {
            synchronized (RetrofitUtil.class) {
                if (util == null) {
                    util = new RetrofitUtil();
                }
            }
        }
        return util;
    }

    private RetrofitUtil() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(ConstValues.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String bindInfo = SharedUtils.singleton().get(ConstValues.THIRD_LOGIN_BIND_INFO,"");
                if(StringUtil.isBlank(bindInfo)){
                    bindInfo = "";
                }
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                Request build = builder.addHeader("Authorization", SharedUtils.getToken())
                        .addHeader(ConstValues.THIRD_LOGIN_BIND_INFO,bindInfo).build();
                return chain.proceed(build);
            }
        });


        httpClientBuilder.addInterceptor(new MyInterceptor());//添加日志打印

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstValues.BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        Log.i("HTTP","接口请求retrofit："+ retrofit.toString());
    }

    public ApiService apiService() {
        return apiService;
    }


}
