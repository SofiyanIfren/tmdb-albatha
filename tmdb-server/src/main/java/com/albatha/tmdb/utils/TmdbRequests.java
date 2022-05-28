package com.albatha.tmdb.utils;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TmdbRequests {
    
    public JSONObject getDiscoveredMovies() throws JSONException, IOException{
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TmdbConstants.TMDB_URL_DISCOVER).newBuilder();
        urlBuilder.addQueryParameter(TmdbConstants.TMDB_QUERY_API_KEY, TmdbConstants.TMDB_API_KEY);
        urlBuilder.addQueryParameter(TmdbConstants.TMDB_QUERY_SORT_BY, TmdbConstants.TMDB_FIELD_POPULARITY_DESC);
		String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
	    Call call = client.newCall(request);
        return new JSONObject(call.execute().body().string());
    }

    public JSONObject getMovieByName(String movieName) throws JSONException, IOException{
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TmdbConstants.TMDB_URL_SEARCH).newBuilder();
        urlBuilder.addQueryParameter(TmdbConstants.TMDB_QUERY_API_KEY, TmdbConstants.TMDB_API_KEY);
        urlBuilder.addQueryParameter(TmdbConstants.TMDB_QUERY_QUERY, movieName);
		String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
	    Call call = client.newCall(request);
        return new JSONObject(call.execute().body().string());
    }

    public JSONObject getMovieById(String movieId) throws JSONException, IOException{
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(TmdbConstants.TMDB_URL_DETAILS + movieId).newBuilder();
        urlBuilder.addQueryParameter(TmdbConstants.TMDB_QUERY_API_KEY, TmdbConstants.TMDB_API_KEY);
		String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
	    Call call = client.newCall(request);
        return new JSONObject(call.execute().body().string());
    }

}
