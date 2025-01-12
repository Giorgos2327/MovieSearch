package com.aliric.moviesearch.repository

import android.content.Context
import com.aliric.moviesearch.api.RetrofitInstance
import com.aliric.moviesearch.jason.Movie
import com.aliric.moviesearch.jason.MovieDetailsModel


class MovieRepository() {

    suspend fun getMoviesFromApi(apiKey:String,query:String):List<Movie>{
        return RetrofitInstance.api.getMovies(apiKey,query).results
    }

    suspend fun getMovieDetailsFromApi(movieId:Int,apiKey: String):MovieDetailsModel{
        return RetrofitInstance.api.getDetails(movieId,apiKey)
    }
}