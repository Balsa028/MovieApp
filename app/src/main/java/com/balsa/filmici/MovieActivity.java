package com.balsa.filmici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.balsa.filmici.Models.MovieModel;
import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {

    private ImageView movieImage;
    private TextView txtDescription,txtTitle;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initViews();

        //recieving data with intent
        Intent intent = getIntent();
        if(intent != null){
            MovieModel movie = intent.getParcelableExtra("movie");
            if(movie != null){
                txtTitle.setText(movie.getTitle());
                txtDescription.setText(movie.getOverview());
                //dividing by two cause rating is from 0 to 10 on web , and in app is 5 stars range
                ratingBar.setRating((movie.getVote_avarage())/2);
                Glide.with(MovieActivity.this)
                        .asBitmap()
                        .load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path())
                        .into(movieImage);
            }
        }




    }

    private void initViews() {
        movieImage = findViewById(R.id.imageViewMovie);
        txtDescription = findViewById(R.id.txtDescription);
        txtTitle = findViewById(R.id.txtTitle);
        ratingBar = findViewById(R.id.ratingBarMovie);

    }
}