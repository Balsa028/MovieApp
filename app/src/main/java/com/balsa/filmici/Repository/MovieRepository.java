package com.balsa.filmici.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.Request.MovieApiClient;

import java.util.List;

public class MovieRepository {


    //making this class in singelton pattern
    private static MovieRepository instance;
    private MovieApiClient movieApiClient;

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovieList() {
        return movieApiClient.getMovieList();
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return movieApiClient.getPopularMovies();
    }

    public static MovieRepository getInstance() {
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    //calling method from movieApiClient
    public void searchMoviesByQuery(String query){
        movieApiClient.searchMoviesByQuery(query);
    }

    //calling method from movieApiClient to get popular items
    public void getMoviesByPopularity(){
        movieApiClient.getMoviesByPopularity();
    }

}
