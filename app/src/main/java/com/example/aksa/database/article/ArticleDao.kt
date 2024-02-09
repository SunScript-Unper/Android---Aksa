package com.example.aksa.database.article

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticle(): PagingSource<Int, ArticleEntity>

}