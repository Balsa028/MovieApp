package com.balsa.filmici.Response;

import com.balsa.filmici.Models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> Movies;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public int getTotalResults(){
        return totalResults;
    }

    public List<MovieModel> getMovies(){
        return Movies;
    }


    @Override
    public String toString() {
        return "PopularMoviesResponse{" +
                "popularMovies=" + Movies +
                ", totalResults=" + totalResults +
                '}';
    }
}
