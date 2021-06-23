package com.balsa.filmici;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.balsa.filmici.Adapters.MovieAdapter;
import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.ViewModels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.interfaces.PBEKey;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Tag";

    public static boolean isPopular = true;

    //view model
    private MovieListViewModel movieListViewModel;

    //defining recView
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private TextView txtPopularHeader;
    private Toolbar toolbar;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPopularHeader = findViewById(R.id.txtPopular);
        
        //getting toolbar
         toolbar = findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);

        //getting view model with help of Provider class from lifecycle lib.
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        initRecView();
        observeChanges();
        observeChangesForPopularMovies();
        setSearchView();

        //getting popular movies
        getMoviesByPopularity();

    }




    private void getSupportActionBar(Toolbar toolbar) {
    }

    //creating search method getting data from search bar and passing to our method for getting data from api
    private void setSearchView() {
        searchView = findViewById(R.id.searchViewID);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMoviesByQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
                txtPopularHeader.setVisibility(View.GONE);
            }
        });
    }

    //method for init recView and filling with data
    private void initRecView() {

        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

    }


    private void observeChangesForPopularMovies() {
        movieListViewModel.getPopularMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                        adapter.setMovies((ArrayList<MovieModel>)movieModels);
                }
            }
        });
    }

    //method for observing data changed
    private void observeChanges(){
        movieListViewModel.getMovieList().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                    adapter.setMovies((ArrayList<MovieModel>)movieModels);
                }
            }
        });
    }

    private void searchMoviesByQuery(String query){
        movieListViewModel.searchMoviesByQuery(query);
    }
    private void getMoviesByPopularity(){
        movieListViewModel.getMoviesByPopularity();
    }

}