package com.aliric.moviesearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aliric.moviesearch.jason.Movie
import com.aliric.moviesearch.jason.MovieDetailsModel
import com.aliric.moviesearch.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository:MovieRepository):ViewModel() {


    var details by mutableStateOf<MovieDetailsModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set


    var errorMessage by mutableStateOf<String?>(null)




    fun fetchDetails(movieId:Int,apiKey:String){
        viewModelScope.launch {
            isLoading=true
            errorMessage=null
            try {
                details=repository.getMovieDetailsFromApi(movieId,"1c27a4d25f2dfd5a77bd7be585bc84d2")
            }catch (e:Exception){
                errorMessage="Failed to load data details:${e.message}"
            }finally {
                isLoading=false
            }
        }
    }
}