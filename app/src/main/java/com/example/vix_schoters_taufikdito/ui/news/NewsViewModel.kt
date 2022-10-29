package com.example.vix_schoters_taufikdito.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vix_schoters_taufikdito.data.NewsRepository
import com.example.vix_schoters_taufikdito.data.local.entity.NewsEntity
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()
    
    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()
    
    fun saveNews(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news, true)
        }
    }
    
    fun deleteNews(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news, false)
        }
    }
}