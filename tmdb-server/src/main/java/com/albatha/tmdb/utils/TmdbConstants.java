package com.albatha.tmdb.utils;

public class TmdbConstants {
    public static final String TMDB_API_KEY        = "";
	
	public static final String TMDB_URL_DISCOVER   = "https://api.themoviedb.org/3/discover/movie/";
	public static final String TMDB_URL_SEARCH     = "https://api.themoviedb.org/3/search/movie/";
	public static final String TMDB_URL_DETAILS    = "https://api.themoviedb.org/3/movie/";
	
	public static final String TMDB_QUERY_API_KEY  = "api_key";
	public static final String TMDB_QUERY_SORT_BY  = "sort_by";
	public static final String TMDB_QUERY_QUERY    = "query";

	public static final String TMDB_FIELD_RESULTS      		= "results";
	public static final String TMDB_FIELD_POPULARITY_DESC   = "popularity.desc";
    
	public static final String ERROR_FETCHING_DATA 				= "Sorry, we had a problem fetching your requested datas";
	public static final String ERROR_MAX_BOOKINGS 				= "Sorry, it seems that you have more than 10 bookings for this movie";
	public static final String ERROR_REGISTER_BEFORE_BOOKING 	= "You have to register before booking any movie";
	public static final String ERROR_USER 						= "Sorry, the requested user cannot be created";
	public static final String ERROR_USER_ALREADY_EXISTS		= "User already exists in database";
	public static final String ERROR_USER_DOESNT_EXISTS			= "User doesn't exists in database";
	
}