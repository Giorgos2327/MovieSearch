package com.aliric.moviesearch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aliric.moviesearch.MoviesViewModel
import com.aliric.moviesearch.jason.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: MoviesViewModel,onMovieClick:(Int)->Unit){

    val searchbar= remember {
        mutableStateOf(TextFieldValue(""))
    }

    val movieList=viewModel.search

    val keyboardController= LocalSoftwareKeyboardController.current



    if (viewModel.isLoading){
        CircularProgressIndicator(color = Color.White)
    }else if (viewModel.errorMessage!=null){
        Text(text = viewModel.errorMessage!!, color = Color.Red)
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .clickable(interactionSource = MutableInteractionSource(), indication = null) {
            keyboardController?.hide()
        })
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = "MovieApp", fontSize = 25.sp, color = Color(0xFF630E72), fontWeight = FontWeight.SemiBold)
            IconButton(onClick = {})

            {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.size(30.dp), tint = Color(0xFF630E72))

            }
            
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = searchbar.value,
            onValueChange = {
                searchbar.value=it

                    viewModel.searchMovies("1c27a4d25f2dfd5a77bd7be585bc84d2", searchbar.value.text)

                            },
            leadingIcon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "SearchBar"
            )}, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFD686D8),
                unfocusedBorderColor = Color(0xFFD686D8),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ))

        MovieList(movies = movieList, onMovieClick = onMovieClick)

    }
}