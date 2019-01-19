package com.avansprojects.antl.retrofit;

import java.net.CookieManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class OkHttpProvider {
    private static OkHttpClient instance = null;

    public static OkHttpClient getOkHttpInstance() {
        if (instance == null) {
            instance = newOkHttpInstance();
        }
        return instance;
    }

    private static OkHttpClient newOkHttpInstance(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.cookieJar(new JavaNetCookieJar(new CookieManager()));

//        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        okhttpClientBuilder.addInterceptor(logging);

        return okhttpClientBuilder.build();
    }
}