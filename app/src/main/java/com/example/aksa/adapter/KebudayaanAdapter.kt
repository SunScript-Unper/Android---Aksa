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
import com.bumptech.glide.Glide
import com.example.aksa.DetailKebudayaanActivity
import com.example.aksa.R

import com.example.aksa.dataKebudayaan.Kebudayaan


class KebudayaanAdapter : ListAdapter<Kebudayaan, KebudayaanAdapter.KebudayaanViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KebudayaanAdapter.KebudayaanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_kebudayaan,
            parent,
            false
        )
        val holder = KebudayaanViewHolder(itemView)
        holder.itemView.setOnClickListener {
            val position =holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                val intent = Intent(itemView.context, DetailKebudayaanActivity::class.java)
                intent.putExtra("kebudayaan", item)
                itemView.context.startActivity(intent)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: KebudayaanAdapter.KebudayaanViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.imageView.setImageResource(currentItem.img)
        holder.titleTextView.text = currentItem.title
        holder.descTextView.text = currentItem.desc1
    }

    class KebudayaanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_kebudayaan)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title_kebudayaan)
        val descTextView: TextView = itemView.findViewById(R.id.tv_desc_kebudayaan)


    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Kebudayaan>() {
            override fun areItemsTheSame(oldItem: Kebudayaan, newItem: Kebudayaan): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Kebudayaan, newItem: Kebudayaan): Boolean {
                return oldItem == newItem
            }
        }
    }

}