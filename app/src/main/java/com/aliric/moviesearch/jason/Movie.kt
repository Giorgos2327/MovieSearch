package com.aliric.moviesearch.jason

import androidx.room.Entity
import androidx.room.PrimaryKey



data class Movie(

    val id:Int,
    val title:String,
    val overview:String,
    val poster_path:String?
)
