package com.example.aksa.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.ConfigNameQuizActivity
import com.example.aksa.R
import com.example.aksa.databinding.FragmentTesBinding
import com.example.aksa.list.ListKebudayaanActivity
import com.example.aksa.list.ListMakananActivity
import com.example.aksa.quiz.Quiz
import com.example.aksa.quiz.QuizAksaraSundaActivity
import com.example.aksa.quiz.QuizKebudayaanActivity
import com.example.aksa.quiz.QuizPupuhActivity

class QuizAdapter: ListAdapter<Quiz, QuizAdapter.QuizHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)

        val holderItem  = QuizHolder(view)

        holderItem.itemView.setOnClickListener {
            val position = holderItem.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val quiz = getItem(position)
                val intent = when (quiz.id) {
                    1 -> {
                        val intent = Intent(view.context, QuizAksaraSundaActivity::class.java)
                        intent.putExtra("KEY_AKSARA", quiz.name)
                        intent
                    }
                    2 -> {
                        val intent = Intent(view.context, QuizKebudayaanActivity::class.java)
                        intent.putExtra("KEY_KEBUDAYAAN", quiz.name)
                        intent
                    }
                    3 -> {
                        val intent = Intent(view.context, QuizPupuhActivity::class.java)
                        intent.putExtra("KEY_PUPUH", quiz.name)
                        intent
                    }
                    else -> {
                        Intent(view.context, FragmentTesBinding::class.java)
                    }
                }
                view.context.startActivity(intent)
            }
        }

        return holderItem
    }

    override fun onBindViewHolder(holder: QuizAdapter.QuizHolder, position: Int) {
        val quiz = getItem(position)
        holder.text.text = quiz.name
    }

    class QuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text : TextView = itemView.findViewById(R.id.tv_quiz)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Quiz>() {
            override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                return oldItem == newItem
            }

        }
    }
}