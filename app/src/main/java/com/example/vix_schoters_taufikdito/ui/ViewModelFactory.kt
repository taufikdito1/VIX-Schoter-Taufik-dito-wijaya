package com.example.vix_schoters_taufikdito.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vix_schoters_taufikdito.data.NewsRepository
import com.example.vix_schoters_taufikdito.di.Injection
import com.example.vix_schoters_taufikdito.ui.news.NewsViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val newsRepository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }
    
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}