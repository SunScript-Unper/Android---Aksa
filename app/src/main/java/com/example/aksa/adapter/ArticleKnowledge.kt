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
import com.example.aksa.list.ListAksaraSundaActivity
import com.example.aksa.list.ListKebudayaanActivity
import com.example.aksa.list.ListMakananActivity
import com.example.aksa.list.ListMitosActivity
import com.example.aksa.list.ListPupuhActivity
import com.example.aksa.utils.Knowledges


class ArticleKnowledge : ListAdapter<Knowledges, ArticleKnowledge.ArticleViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleKnowledge.ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_knowledge,
            parent,
            false
        )
        val holderItem = ArticleViewHolder(itemView)

        holderItem.itemView.setOnClickListener {
            val position = holderItem.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                val intent = when (item.id) {
                    1 -> Intent(itemView.context, ListKebudayaanActivity::class.java)
                    2 -> Intent(itemView.context, ListPupuhActivity::class.java)
                    3 -> Intent(itemView.context, ListMitosActivity::class.java)
                    4 -> Intent(itemView.context, ListAksaraSundaActivity::class.java)
                    5 -> Intent(itemView.context, ListMakananActivity::class.java)
                    else -> {
                        Intent(itemView.context, ListKebudayaanActivity::class.java)
                    }
                }
                itemView.context.startActivity(intent)
            }
        }
        return holderItem


    }

    override fun onBindViewHolder(holder: ArticleKnowledge.ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.imageView.setImageResource(item.img)
        holder.textViewTitle.text = item.title
    }

    class ArticleViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
    {
        val textViewTitle: TextView = itemView.findViewById(R.id.tv_title_knowledge)
        val imageView : ImageView = itemView.findViewById(R.id.tv_image_knowledge)

    }

    private class ArticleDiffCallback : DiffUtil.ItemCallback<Knowledges>() {
        override fun areItemsTheSame(oldItem: Knowledges, newItem: Knowledges): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Knowledges, newItem: Knowledges): Boolean {
            return oldItem == newItem
        }
    }
}