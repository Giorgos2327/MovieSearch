package com.aliric.moviesearch.api

import com.aliric.moviesearch.jason.MovieDetailsModel
import com.aliric.moviesearch.jason.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey:String,
        @Query("query") query:String
    ):MovieResponse


    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId:Int,
        @Query("api_key") apiKey:String
    ):MovieDetailsModel
}