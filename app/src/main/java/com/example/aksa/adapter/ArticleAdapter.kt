package com.example.aksa.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.DetailArticleActivity
import com.example.aksa.R
import com.example.aksa.database.article.Article


class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_article,
            parent,
            false
        )
        val holderItem = ArticleViewHolder(itemView)

        holderItem.itemView.setOnClickListener {
            val position = holderItem.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                val intent = Intent(itemView.context, DetailArticleActivity::class.java)
                intent.putExtra("article", item)
                itemView.context.startActivity(intent)
            }
        }
        return holderItem


    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.imageView.setImageResource(item.image)
        holder.textViewTitle.text = item.title
        holder.textViewDesc.text = item.description
    }

    class ArticleViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
    {
        val textViewTitle: TextView = itemView.findViewById(R.id.tv_title_article)
        val textViewDesc: TextView = itemView.findViewById(R.id.tv_description_article)
        val imageView : ImageView = itemView.findViewById(R.id.tv_image_article)

    }

    private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}