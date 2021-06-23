package com.balsa.filmici.Utils;

import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.Response.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitEndPoint {

    //get request for searching for list of movies containing QUERY string
    @GET("/3/search/movie?")
    Call<MovieListResponse> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query);

    //getting for example popular movies
    @GET("/3/movie/popular?")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String api_key);

    //getting single movie by id
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovieById (@Path("movie_id") int movie_id, @Query("api_key") String api_key);

}
