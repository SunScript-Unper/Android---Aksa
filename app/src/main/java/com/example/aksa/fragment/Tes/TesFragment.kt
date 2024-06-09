package com.example.aksa.fragment.Tes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aksa.R
import com.example.aksa.adapter.QuizAdapter
import com.example.aksa.databinding.FragmentTesBinding
import com.example.aksa.quiz.DataQuiz
import com.example.aksa.quiz.Quiz


class TesFragment : Fragment() {

    private lateinit var binding: FragmentTesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentTesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val adapter = QuizAdapter()
        binding.rvQuiz.adapter = adapter
        binding.rvQuiz.setHasFixedSize(true)
        adapter.submitList(DataQuiz.listQuiz)


        return binding.root
    }

}