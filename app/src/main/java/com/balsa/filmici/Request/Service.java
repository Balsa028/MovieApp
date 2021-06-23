package com.balsa.filmici.Request;

import com.balsa.filmici.Utils.Credentials;
import com.balsa.filmici.Utils.RetrofitEndPoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    //creating retrofit object for using in whole app
    private static Retrofit retrofit = builder.build();

    private static RetrofitEndPoint  retrofitEndPoint = retrofit.create(RetrofitEndPoint.class);

    //getter for interface
    public static RetrofitEndPoint getRetrofitEndPoint(){
        return retrofitEndPoint;
    }
}
