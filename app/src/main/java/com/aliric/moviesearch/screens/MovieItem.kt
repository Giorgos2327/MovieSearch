package com.aliric.moviesearch.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aliric.moviesearch.jason.Movie

@Composable
fun MovieItem(movie: Movie,onClick:()->Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
            contentDescription = "Movie Image",

            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                .size(200.dp)
                .padding(bottom = 4.dp),
            contentScale = ContentScale.Crop
                )
        
        Text(
            text = movie.title,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp))

    }
}