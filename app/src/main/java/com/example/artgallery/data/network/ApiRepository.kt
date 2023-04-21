package com.example.artgallery.data.network

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllArts() = apiService.getAllArts()
}