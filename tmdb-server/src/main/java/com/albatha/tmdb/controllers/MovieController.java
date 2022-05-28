package com.albatha.tmdb.controllers;

import java.util.List;

import com.albatha.tmdb.models.tmdb.Movie;
import com.albatha.tmdb.models.tmdb.MovieDetails;
import com.albatha.tmdb.services.interfaces.MovieService;
import com.albatha.tmdb.utils.TmdbConstants;
import com.albatha.tmdb.utils.exceptions.MovieException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @CrossOrigin
    @GetMapping("/discover")
    public List<Movie> getDiscoveredMoviesList(){
        List<Movie> movieList;
        try {
            movieList = movieService.discoverMovies();
            return movieList;
        } catch (Exception e) {
            log.error(e.toString());
            throw new MovieException(TmdbConstants.ERROR_FETCHING_DATA);
        }
    }

    @CrossOrigin
    @GetMapping("/search/{movieName}") 
    public List<Movie> searchMovieListByName(@PathVariable String movieName){
        List<Movie> movieList;
        try {
            movieList = movieService.searchMovieListByName(movieName);
            return movieList;
        } catch (Exception e) {
            log.error(e.toString());
            throw new MovieException(TmdbConstants.ERROR_FETCHING_DATA);
        }
    }

    @CrossOrigin
    @GetMapping("/details/{movieId}") 
    public MovieDetails findMovieDetailsById(@PathVariable String movieId){
        MovieDetails movieDetails;
        try {
            movieDetails = movieService.findMovieDetailsByMovieId(movieId);
            return movieDetails;
        } catch (Exception e) {
            log.error(e.toString());
            throw new MovieException(TmdbConstants.ERROR_FETCHING_DATA);
        }
    }
    
}
