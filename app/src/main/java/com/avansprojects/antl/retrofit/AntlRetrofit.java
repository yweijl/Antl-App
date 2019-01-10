package com.avansprojects.antl.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AntlRetrofit {

    private Retrofit mRetrofit;
    private final static AntlRetrofit mInstance = new AntlRetrofit();

    private AntlRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Gson gson = new GsonBuilder()
                .setLenient().create();

            mRetrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:64151")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
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
