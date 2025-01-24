package com.aliric.moviesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aliric.moviesearch.repository.MovieRepository
import com.aliric.moviesearch.screens.MovieDetailsScreen
import com.aliric.moviesearch.screens.MovieList
import com.aliric.moviesearch.screens.SearchScreen
import com.aliric.moviesearch.ui.theme.MovieSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val repository=MovieRepository()

        val viewModelFactory=MovieViewModelFactory(repository)

        val viewModel=ViewModelProvider(this,viewModelFactory)[MoviesViewModel::class.java]
        setContent {
            MovieSearchTheme {
                MyNavigation(repository = repository)


            }
        }
    }
}


@Composable
fun MyNavigation(repository:MovieRepository){

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "movieList") {

        composable("movieList") {
            val viewModel = MoviesViewModel(repository)
            SearchScreen(viewModel = viewModel, onMovieClick = { movieId ->
                navController.navigate("MovieDetailScreen/$movieId")
            }, )


        }
        composable("MovieDetailScreen/{movieId}", arguments = listOf(navArgument("movieId") {
            type =
                NavType.IntType
        }))
        { navBackStackEntry ->

            val movieId = navBackStackEntry.arguments?.getInt("movieId") ?: 0
            val detailsViewModel = MovieDetailsViewModel(repository)

            detailsViewModel.fetchDetails(movieId, "1c27a4d25f2dfd5a77bd7be585bc84d2")
            MovieDetailsScreen(viewModel = detailsViewModel,navController)

        }
    }
}
