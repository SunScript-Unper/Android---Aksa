package com.example.aksa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aksa.database.article.Article
import com.example.aksa.database.article.Articles
import com.example.aksa.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var article : Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        article = intent.getParcelableExtra("article") ?: Article(0, "", "", 0)

        binding.tvImageDetailArticle.setImageResource(article.image)
        binding.tvTitleDetailArticle.text = article.title
        binding.tvDescriptionDetailArticle.text = article.description
    }
}