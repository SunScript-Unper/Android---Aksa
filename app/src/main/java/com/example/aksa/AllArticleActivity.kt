package com.example.aksa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aksa.adapter.ArticleAdapter
import com.example.aksa.database.article.Articles
import com.example.aksa.databinding.ActivityAllArticleBinding

class AllArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArticleAdapter()
        val recyclerView = binding.allRvArticle
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        adapter.submitList(Articles.article)
    }
}