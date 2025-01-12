package com.aliric.moviesearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliric.moviesearch.jason.Movie
import com.aliric.moviesearch.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository:MovieRepository):ViewModel() {


    var search by mutableStateOf<List<Movie>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)



    fun searchMovies(apiKey:String,query:String){
        viewModelScope.launch {
            isLoading=true
            errorMessage=null
            try {
                search=repository.getMoviesFromApi("1c27a4d25f2dfd5a77bd7be585bc84d2",query)
            }catch (e:Exception){
                errorMessage="Failed to load data:${e.message}"
            }finally {
                isLoading=false
            }

        }
    }
}