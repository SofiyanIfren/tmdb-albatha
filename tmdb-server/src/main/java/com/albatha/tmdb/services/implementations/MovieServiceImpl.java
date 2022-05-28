package com.albatha.tmdb.services.implementations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.albatha.tmdb.models.tmdb.Movie;
import com.albatha.tmdb.models.tmdb.MovieDetails;
import com.albatha.tmdb.services.interfaces.MovieService;
import com.albatha.tmdb.utils.TmdbConstants;
import com.albatha.tmdb.utils.TmdbRequests;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service(value="movieService")
public class MovieServiceImpl implements MovieService {

    @Override
    public List<Movie> discoverMovies() throws JSONException, IOException {
        JSONObject apiData      = new TmdbRequests().getDiscoveredMovies();
		JSONArray jsonMovieList = new JSONArray(apiData.get(TmdbConstants.TMDB_FIELD_RESULTS).toString());
        List<Movie> movieList   =  Arrays.asList(new ObjectMapper().readValue(jsonMovieList.toString(), Movie[].class));
        return movieList.stream().limit(10).toList(); // returns only the last 10 entries, sorted by popularity desc
    }

    @Override
    public List<Movie> searchMovieListByName(String movieName) throws JSONException, IOException {
        JSONObject apiData      = new TmdbRequests().getMovieByName(movieName);
        JSONArray jsonMovieList = new JSONArray(apiData.get(TmdbConstants.TMDB_FIELD_RESULTS).toString());
        return Arrays.asList(new ObjectMapper().readValue(jsonMovieList.toString(), Movie[].class));
    }

    @Override
    public Movie searchMovieByDescription(String movieDescription) {
        return null;  // TODO Search further in the doc if it is possible to add this filter
    }

    @Override
    public MovieDetails findMovieDetailsByMovieId(String movieId) throws JSONException, IOException {
        JSONObject apiData      = new TmdbRequests().getMovieById(movieId);
        return new ObjectMapper().readValue(apiData.toString(), MovieDetails.class);
    }
    
}
