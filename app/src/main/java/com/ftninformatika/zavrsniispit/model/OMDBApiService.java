package com.ftninformatika.zavrsniispit.model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDBApiService {


    static OkHttpClient okHttpClient = new OkHttpClient();

    public static Retrofit getRetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Contract.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
    public static OMDBApiEndpoint apiInterface(){
        OMDBApiEndpoint apiService = getRetrofitInstance().create(OMDBApiEndpoint.class);
        return apiService;
    }


}
