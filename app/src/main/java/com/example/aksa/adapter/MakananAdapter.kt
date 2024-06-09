package com.example.aksa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.R
import com.example.aksa.dataMakanan.Makanan

class MakananAdapter: ListAdapter<Makanan, MakananAdapter.MakananViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MakananAdapter.MakananViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
        return MakananViewHolder(view)
    }

    override fun onBindViewHolder(holder: MakananAdapter.MakananViewHolder, position: Int) {
        val makanan = getItem(position)
        holder.text.text = makanan.name
        holder.image.setImageResource(makanan.img)
    }

    class MakananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.nama_makanan)
        val image: ImageView = itemView.findViewById(R.id.image_makanan)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Makanan>() {
            override fun areItemsTheSame(oldItem: Makanan, newItem: Makanan): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Makanan, newItem: Makanan): Boolean {
                return oldItem == newItem
            }
        }
    }
}