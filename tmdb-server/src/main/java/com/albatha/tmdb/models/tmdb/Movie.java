package com.albatha.tmdb.models.tmdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movie {

    private String adult;
	private String backdrop_path;
	private List<Integer> genre_ids;
	private String id;
	private String original_language;
	private String original_title;
	private String overview;
	private String popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private String video;
	private String vote_average;
	private String vote_count;

}
