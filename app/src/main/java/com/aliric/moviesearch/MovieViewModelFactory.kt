package com.aliric.moviesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliric.moviesearch.repository.MovieRepository

class MovieViewModelFactory(private val repository:MovieRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")

    }
}