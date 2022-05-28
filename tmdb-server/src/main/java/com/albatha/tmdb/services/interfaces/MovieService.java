package com.albatha.tmdb.services.interfaces;

import java.io.IOException;
import java.util.List;

import com.albatha.tmdb.models.tmdb.Movie;
import com.albatha.tmdb.models.tmdb.MovieDetails;

import org.json.JSONException;

public interface MovieService {
    
    List<Movie>  discoverMovies() throws JSONException, IOException;
    List<Movie>  searchMovieListByName(String movieName) throws JSONException, IOException;
    Movie        searchMovieByDescription(String movieDescription);
    MovieDetails findMovieDetailsByMovieId(String movieId) throws JSONException, IOException;

}
