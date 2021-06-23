package com.balsa.filmici.Request;

import android.util.Log;

import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.balsa.filmici.AppExecutors;
import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.Response.MovieListResponse;
import com.balsa.filmici.Utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {


    //live data for search query results
    private MutableLiveData<List<MovieModel>> movieList;
    //live data for popular movie
    private MutableLiveData<List<MovieModel>> popularMovies;

    private static MovieApiClient instance;

    private RetrieveMovieRunnable retrieveMovieRunnable;
    private RetrievePopularMovieRunnable retrievePopularMovieRunnable;

    //constructor
    public MovieApiClient() {
        movieList = new MutableLiveData<>();
        popularMovies = new MutableLiveData<>();
    }

    //creating method for searching movies that will be called by classes and retrieve data all the way to UI
    public void searchMoviesByQuery(String query){

        //checking if our runnable is null if it is setting it to null
        if(retrieveMovieRunnable != null){
            retrieveMovieRunnable = null;
        }
        //instantiating runnable and down using it in sumbit function
        retrieveMovieRunnable = new RetrieveMovieRunnable(query);

        final Future handler = AppExecutors.getInstance().getService().submit(retrieveMovieRunnable);

        AppExecutors.getInstance().getService().schedule(new Runnable() {
            @Override
            public void run() {
                //canceling the retrofit call
                handler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);


    }

    public void getMoviesByPopularity(){

        if(retrievePopularMovieRunnable != null){
            retrievePopularMovieRunnable = null;
        }
        retrievePopularMovieRunnable = new RetrievePopularMovieRunnable();

        //retrieving data in backthread
        final Future handlerPopularMovies = AppExecutors.getInstance().getService().submit(retrievePopularMovieRunnable);

        //canceling handler
        AppExecutors.getInstance().getService().schedule(new Runnable() {
            @Override
            public void run() {
                handlerPopularMovies.cancel(true);
            }
        },3000,TimeUnit.MILLISECONDS);

    }



    //getters
    public LiveData<List<MovieModel>> getMovieList() {
        return movieList;
    }

    public LiveData<List<MovieModel>> getPopularMovies() {
        return popularMovies;
    }

    public static MovieApiClient getInstance() {
        if(instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }


 // creating runnable class for retrieving data from restAPI
    private class RetrieveMovieRunnable implements Runnable{

        private String query;
        boolean cancelRequest;

     public RetrieveMovieRunnable(String query) {
         this.query = query;
         this.cancelRequest = false;
     }

     @Override
     public void run() {

         try{
             //getting response object
             Response response = getMoviesByQuery(query).execute();
             //checking if cancelRequest field is true or false
             if(cancelRequest) {
                 return;
             }

             if(response.code() == 200){
                 // we need to cast it to movieListResponse class for accessing getMovies method
                 ArrayList<MovieModel> movies = (ArrayList<MovieModel>)((MovieListResponse)response.body()).getMovies();
                //posting value on live data
                 movieList.postValue(movies);
             }else {
                 Log.d("Error", "run: "+response.errorBody().string());
                 movieList.postValue(null);
             }

         } catch (IOException e) {
             e.printStackTrace();
             movieList.postValue(null);
         }


     }
     //creating method here for getting movies
     private Call<MovieListResponse> getMoviesByQuery (String query){
         return Service.getRetrofitEndPoint().getMoviesByQuery(Credentials.API_KEY,query);
     }
     //creating method for canceling
     private void requestCanceled(){
         cancelRequest = true;
     }
 }

    // creating runnable class for retreiving popular movie list
    private class RetrievePopularMovieRunnable implements Runnable{

        boolean cancelRequest;

        public RetrievePopularMovieRunnable() {
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            try{
                //getting response object
                Response response = getPopularMovies().execute();
                //checking if cancelRequest field is true or false
                if(cancelRequest) {
                    return;
                }

                if(response.code() == 200){
                    // we need to cast it to movieListResponse class for accessing getMovies method
                    ArrayList<MovieModel> movies = (ArrayList<MovieModel>)((MovieListResponse)response.body()).getMovies();
                    //posting value on live data
                    popularMovies.postValue(movies);
                }else {
                    Log.d("Error", "run: "+response.errorBody().string());
                    popularMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                popularMovies.postValue(null);
            }


        }
        //creating method here for getting movies
        private Call<MovieListResponse> getPopularMovies (){
            return Service.getRetrofitEndPoint().getPopularMovies(Credentials.API_KEY);
        }
        //creating method for canceling
        private void requestCanceled(){
            cancelRequest = true;
        }
    }


}
