package com.balsa.filmici.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balsa.filmici.Models.MovieModel;
import com.balsa.filmici.MovieActivity;
import com.balsa.filmici.R;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.balsa.filmici.MainActivity.isPopular;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final int POPULAR_MOVIE_DISPLAY = 1;
    public static final int SEARCH_RESULT_DISPLAY = 2;

    private Context context;
    private ArrayList<MovieModel> movies = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        if(viewType == SEARCH_RESULT_DISPLAY){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false);
            return new ViewHolder(view);
        }
        else if(viewType == POPULAR_MOVIE_DISPLAY){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movies_list_item,parent,false);
            return new ViewHolder(view);
        }
        else{
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MovieAdapter.ViewHolder holder, int position) {
        //setting all field of layout with values from array
        String rate = String.valueOf(movies.get(position).getVote_avarage());
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.rate.setText("Rate: "+rate);
       // holder.releaseDate.setText(movies.get(position).getRelease_date());
       // holder.ratingBar.setRating((movies.get(position).getVote_avarage())/2);
        Glide.with(context)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w500/"+movies.get(position).getPoster_path())
                .into(holder.movieImage);


        //setting on click listener
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirecting user to movie activity and passing whole movie
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movie",movies.get(position));
                context.startActivity(intent);
            }
        });


    }

    //creating this method so we can import certain data in certain cases
    @Override
    public int getItemViewType(int position) {
        if (isPopular) {
            return POPULAR_MOVIE_DISPLAY;
        }
        else{
            return SEARCH_RESULT_DISPLAY;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    //creating inner ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialCardView parent;
        private ImageView movieImage;
        private TextView movieTitle,releaseDate,rate;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.txtMovieTitle);
            rate = itemView.findViewById(R.id.txtRate);


        }
    }
}
