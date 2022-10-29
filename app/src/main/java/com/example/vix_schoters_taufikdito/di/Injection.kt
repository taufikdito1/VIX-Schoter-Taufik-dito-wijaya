package com.example.vix_schoters_taufikdito.di

import android.content.Context
import com.example.vix_schoters_taufikdito.data.NewsRepository
import com.example.vix_schoters_taufikdito.data.local.room.NewsDatabase
import com.example.vix_schoters_taufikdito.data.remote.networking.ApiConfig
import com.example.vix_schoters_taufikdito.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService, dao)
    }
}