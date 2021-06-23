package com.balsa.filmici.Response;

import com.balsa.filmici.Models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSingleMovie {
    //using gson library for parsing data
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "SingleMovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
