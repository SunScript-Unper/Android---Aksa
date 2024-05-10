package com.example.aksa.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aksa.AllArticleActivity
import com.example.aksa.ScanActivity
import com.example.aksa.adapter.ArticleKnowledge
import com.example.aksa.database.article.Articles
import com.example.aksa.databinding.FragmentHomeBinding
import com.example.aksa.pref.user.UserPreference
import com.example.aksa.utils.Knowledge


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var userPreference: UserPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userPreference = UserPreference.getInstance(requireContext())


        binding.btnScan.setOnClickListener {
            val intent = Intent(activity, ScanActivity::class.java)
            startActivity(intent)
        }

        binding.tvSeeAll.setOnClickListener {
            val intent = Intent(activity, AllArticleActivity::class.java)
            startActivity(intent)
        }

        val adapter = ArticleKnowledge()
        val recyclerView = binding.rvItemKnowledge
        if (recyclerView != null) {
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }

//        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
//            requireContext(),
//            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
//            false
//        )


        adapter.submitList(Knowledge.knowledge)


        return binding.root
    }


}