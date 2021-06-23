package com.balsa.filmici.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.Repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    //creating empty constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    //creating getter for our Live Data for search query
    public LiveData<List<MovieModel>> getMovieList() {
        return movieRepository.getMovieList();
    }
    //creating getter for our Live Data for popular movies
    public LiveData<List<MovieModel>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }

    //calling method from repository
    public void searchMoviesByQuery(String query){
        movieRepository.searchMoviesByQuery(query);
    }


    //calling method from repository
    public void getMoviesByPopularity(){
        movieRepository.getMoviesByPopularity();
    }
}
