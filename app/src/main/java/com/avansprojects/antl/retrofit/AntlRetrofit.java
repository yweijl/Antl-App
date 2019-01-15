package com.avansprojects.antl.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AntlRetrofit {

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private final static AntlRetrofit mInstance = new AntlRetrofit();

    private AntlRetrofit() {
        mOkHttpClient = provideOkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient().create();

            mRetrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:64151")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();
    }

    private OkHttpClient provideOkHttpClient() {
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


    private static AntlRetrofit Instance()
    {
        return mInstance;
    }

    public static Retrofit getRetrofit()
    {
        return Instance().mRetrofit;
    }
}
