package com.example.aksa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.R
import com.example.aksa.dataSwara.Swara

class SwaraAdapter: ListAdapter<Swara, SwaraAdapter.SwaraViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SwaraAdapter.SwaraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swara, parent, false)
        return SwaraViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwaraAdapter.SwaraViewHolder, position: Int) {
        val swara = getItem(position)
        holder.image.setImageResource(swara.image)
        holder.text.text = swara.name
    }

    class SwaraViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.image_swara)
        val text : TextView = itemView.findViewById(R.id.huruf_swara)
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Swara>() {
            override fun areItemsTheSame(oldItem: Swara, newItem: Swara): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Swara, newItem: Swara): Boolean {
                return oldItem == newItem
            }

        }
    }
}