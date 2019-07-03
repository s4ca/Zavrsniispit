package com.ftninformatika.zavrsniispit.model;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OMDBApiEndpoint {


    @GET("/")
    Call<SearchResult> searchOMDB(@QueryMap Map<String, String> options);

    @GET("/")
    Call<Movie> searchMovieOMDB(@QueryMap Map<String, String> options);


}
