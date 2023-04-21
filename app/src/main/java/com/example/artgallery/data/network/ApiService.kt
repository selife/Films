package com.example.artgallery.data.network

import com.example.artgallery.data.models.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/shows")
    suspend fun getAllArts() : Response<List<Movies>>
}