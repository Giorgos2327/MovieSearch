package com.aliric.moviesearch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aliric.moviesearch.MovieDetailsViewModel
import com.aliric.moviesearch.jason.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    navController: NavController
) {


    val movieDetails = viewModel.details
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage




    if (isLoading) {
        CircularProgressIndicator()
    } else if (errorMessage != null) {
        Text(text = "Error:$errorMessage")
    } else if (movieDetails != null) {


        Box(modifier = Modifier.fillMaxSize()) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            )
            {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movieDetails.poster_path}",
                        contentDescription = "title",

                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp)
                    )
                    {


                        IconButton(onClick = { navController.popBackStack() })
                        {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back",
                                tint = Color.Yellow
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = viewModel.details?.title ?: "Movie Details",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White
                        )
                    }


                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier


                            .size(100.dp)
                            .background(Color.Gray)
                    )


                    {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${movieDetails.poster_path}",
                            contentDescription = "Small Movie Poster",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = movieDetails.title, maxLines = 2,
                        fontSize = 20.sp, color = Color.White,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .weight(2f)
                            .padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {})

                    {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Add to Favorite",
                            tint = Color(0xFF630E72),
                            modifier = Modifier.size(40.dp)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = movieDetails.overview,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}