package com.example.aksa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aksa.adapter.AllKnowledgeAdapter
import com.example.aksa.adapter.ArticleKnowledge
import com.example.aksa.database.article.Articles
import com.example.aksa.databinding.ActivityAllArticleBinding
import com.example.aksa.utils.Knowledge

class AllArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AllKnowledgeAdapter()
        val recyclerView = binding.allRvArticle
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        adapter.submitList(Knowledge.knowledge)
    }
}