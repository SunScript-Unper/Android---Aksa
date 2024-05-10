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
import com.example.aksa.dataNgalagena.Ngalagena

class NgalagenaAdapter: ListAdapter<Ngalagena, NgalagenaAdapter.NgalagenaViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NgalagenaAdapter.NgalagenaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swara, parent, false)
        return NgalagenaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NgalagenaAdapter.NgalagenaViewHolder, position: Int) {
        val swara = getItem(position)
        holder.text.text = swara.name
        holder.image.setImageResource(swara.image)
    }

    class NgalagenaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text : TextView = itemView.findViewById(R.id.huruf_swara)
        val image : ImageView = itemView.findViewById(R.id.image_swara)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ngalagena>(){
            override fun areItemsTheSame(oldItem: Ngalagena, newItem: Ngalagena): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ngalagena, newItem: Ngalagena): Boolean {
                return oldItem == newItem
            }

        }
    }
}