package com.example.administrator.rxjavaretrofitlambdademo.retrofit;

import com.example.administrator.rxjavaretrofitlambdademo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAL
{
    private BaseAPIService baseAPIService;
    public static final DAL shareDAL = new DAL();

    private DAL() {

    }
    public BaseAPIService baseAPIService() {
        if (baseAPIService == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClientBuilder.interceptors().add(interceptor);
            }
                      // .addCallAdapterFactory(DALCallAdapterFactory.create())
            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .baseUrl("http://admin.hkshijue.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            baseAPIService = retrofit.create(BaseAPIService.class);
        }
        return baseAPIService;
    }
}

