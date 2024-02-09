package com.example.aksa.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aksa.adapter.FavoriteAdapter
import com.example.aksa.database.favorite.FavoriteFactory
import com.example.aksa.database.favorite.FavoriteViewModel
import com.example.aksa.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var favoriteViewModel : FavoriteViewModel
    private lateinit var adapter : FavoriteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favoriteViewModel = ViewModelProvider(this, FavoriteFactory.getInstance(requireContext()))[FavoriteViewModel::class.java]

        adapter = FavoriteAdapter()
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)

        favoriteViewModel.getAllFavorite().observe(requireActivity()) { item ->
            adapter.submitData(lifecycle, item)
        }

        binding.deleteAll.setOnClickListener {
            favoriteViewModel.deleteAll()

        }

        return binding.root
    }


}