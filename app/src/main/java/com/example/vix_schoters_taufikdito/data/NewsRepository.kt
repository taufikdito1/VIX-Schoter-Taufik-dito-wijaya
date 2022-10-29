package com.example.vix_schoters_taufikdito.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.vix_schoters_taufikdito.BuildConfig.API_KEY
import com.example.vix_schoters_taufikdito.data.local.entity.NewsEntity
import com.example.vix_schoters_taufikdito.data.local.room.NewsDao
import com.example.vix_schoters_taufikdito.data.remote.networking.ApiService

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(API_KEY)
            val articles = response.articles
            val newsList = articles.map { article ->
                val isBookmarked = newsDao.isNewsBookmarked(article.title)
                NewsEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url,
                    isBookmarked
                )
            }
            newsDao.deleteAll()
            newsDao.insertNews(newsList)
        } catch (e: Exception) {
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<NewsEntity>>> =
            newsDao.getNews().map { Result.Success(it) }
        emitSource(localData)
    }

    suspend fun setNewsBookmark(news: NewsEntity, bookmarkState: Boolean) {
        news.isBookmarked = bookmarkState
        newsDao.updateNews(news)
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao)
            }.also { instance = it }
    }
}