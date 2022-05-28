package com.albatha.tmdb.models.tmdb;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieDetails {
    
    private String adult;
	private String backdrop_path;
	private String belongs_to_collection;
	private String budget;
	private ArrayList<Genre> genres;
	private String homepage;
	private String id;
	private String imdb_id;
	private String original_language;
	private String original_title;
	private String overview;
	private String popularity;
	private String poster_path;
	private ArrayList<ProductionCompany> production_companies;
	private ArrayList<ProductionCountry> production_countries;
    private String release_date;
	private String revenue;
    private String runtime;
    private ArrayList<SpokenLanguage> spoken_languages;
    private String status;
    private String tagline;
    private String title;
	private String video;
	private String vote_average;
	private String vote_count;
    
}
