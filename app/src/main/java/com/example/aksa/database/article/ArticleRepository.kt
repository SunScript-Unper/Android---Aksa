package com.example.aksa.database.article

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class ArticleRepository(private val articleDao: ArticleDao) {

    companion object {
        const val PAGE_SIZE = 30
        const val PLACEHOLDER = true

        @Volatile
        private var instance: ArticleRepository? = null

        fun getInstance(context: Context) : ArticleRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = ArticleDatabase.getInstance(context)
                    instance = ArticleRepository(database.articleDao())
                }
                return instance as ArticleRepository
            }
        }

    }

    fun getAllArticle() : LiveData<PagingData<ArticleEntity>> {
        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = PLACEHOLDER,
        )
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { articleDao.getAllArticle()
            }
        ).liveData
    }
}