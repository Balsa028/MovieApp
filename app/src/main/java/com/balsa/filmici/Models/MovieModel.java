package com.balsa.filmici.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private int id;
    private String title;
    private String release_date;
    private String poster_path;
    private float vote_average;
    private String overview;
    private int runtime;


    public MovieModel(int id, String title, String release_date, String poster_path, float vote_avarage, String overview,int runtime) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_avarage;
        this.overview = overview;
        this.runtime = runtime;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
        runtime = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }


    public String getPoster_path() {
        return poster_path;
    }


    public float getVote_avarage() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", vote_avarage=" + vote_average +
                ", overview='" + overview + '\'' +
                ", runtime=" + runtime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
        dest.writeInt(runtime);
    }
}
