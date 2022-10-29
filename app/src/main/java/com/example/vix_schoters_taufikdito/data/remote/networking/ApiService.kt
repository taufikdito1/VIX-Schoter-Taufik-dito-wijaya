package com.example.vix_schoters_taufikdito.data.remote.networking

import com.example.vix_schoters_taufikdito.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id")
    suspend fun getNews(@Query("apiKey") apiKey: String): NewsResponse
}