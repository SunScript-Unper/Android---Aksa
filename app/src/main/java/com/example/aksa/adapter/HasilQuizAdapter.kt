package com.example.aksa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.database.hasilQuiz.HasilQuizEntity
import com.example.aksa.databinding.ItemHasilQuizBinding

class HasilQuizAdapter : ListAdapter<HasilQuizEntity, HasilQuizAdapter.HasilQuizViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HasilQuizAdapter.HasilQuizViewHolder {
        val binding = ItemHasilQuizBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HasilQuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HasilQuizAdapter.HasilQuizViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class HasilQuizViewHolder(
        private val binding: ItemHasilQuizBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HasilQuizEntity) {
            binding.tvNamaQuiz.text = item.namaQuiz
            binding.tvNilaiQuiz.text = item.nilaiQuiz.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HasilQuizEntity>() {
            override fun areItemsTheSame(
                oldItem: HasilQuizEntity,
                newItem: HasilQuizEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: HasilQuizEntity,
                newItem: HasilQuizEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}