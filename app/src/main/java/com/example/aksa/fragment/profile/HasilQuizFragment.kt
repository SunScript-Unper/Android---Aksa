package com.example.aksa.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aksa.adapter.HasilQuizAdapter
import com.example.aksa.database.hasilQuiz.HasilQuizFactory
import com.example.aksa.database.hasilQuiz.HasilQuizViewModel
import com.example.aksa.databinding.FragmentProfileBinding
import com.example.aksa.pref.user.UserPreference


class HasilQuizFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userPreference: UserPreference
    private lateinit var hasilQuizViewModel: HasilQuizViewModel
    private lateinit var adapter : HasilQuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        hasilQuizViewModel = ViewModelProvider(requireActivity(), HasilQuizFactory.getInstance(requireActivity()))[HasilQuizViewModel::class.java]

        adapter = HasilQuizAdapter()
        binding.rvHasilQuiz.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHasilQuiz.adapter = adapter
        binding.rvHasilQuiz.setHasFixedSize(true)

        hasilQuizViewModel.getAllHasilQuiz().observe(requireActivity()) { item ->
            adapter.submitList(item)
        }

        binding.tvDelete.setOnClickListener {
            hasilQuizViewModel.deleteAll()
        }



        return binding.root
    }

}