package com.example.aksa.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.DetailPupuhActivity
import com.example.aksa.R
import com.example.aksa.dataPupuh.Pupuh

class PupuhAdapter : ListAdapter<Pupuh, PupuhAdapter.PupuhViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PupuhAdapter.PupuhViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pupuh,
            parent,
            false
        )
        val holder = PupuhViewHolder(itemView)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                val intent = Intent(itemView.context, DetailPupuhActivity::class.java)
                intent.putExtra("pupuh", item)
                itemView.context.startActivity(intent)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: PupuhAdapter.PupuhViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.textView.text = currentItem.name
    }

    class PupuhViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_title_pupuh)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pupuh>() {
            override fun areItemsTheSame(oldItem: Pupuh, newItem: Pupuh): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pupuh, newItem: Pupuh): Boolean {
                return oldItem == newItem
            }

        }
    }
}