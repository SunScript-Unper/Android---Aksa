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
import com.example.aksa.DetailMitosActivity
import com.example.aksa.R
import com.example.aksa.dataMitos.Mitos

class MitosAdapter: ListAdapter<Mitos, MitosAdapter.MitosViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MitosAdapter.MitosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_mitos,
            parent,
            false
        )
        val holder = MitosViewHolder(itemView)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                val intent = Intent(itemView.context, DetailMitosActivity::class.java)
                intent.putExtra("mitos", item)
                itemView.context.startActivity(intent)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MitosAdapter.MitosViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.imageView.setImageResource(currentItem.img)
        holder.textView.text = currentItem.name
        holder.desc.text = currentItem.desc
    }

    class MitosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_mitos)
        val textView: TextView = itemView.findViewById(R.id.tv_title_mitos)
        val desc : TextView = itemView.findViewById(R.id.tv_desc_mitos)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Mitos>() {
            override fun areItemsTheSame(oldItem: Mitos, newItem: Mitos): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Mitos, newItem: Mitos): Boolean {
                return oldItem == newItem
            }

        }
    }
}