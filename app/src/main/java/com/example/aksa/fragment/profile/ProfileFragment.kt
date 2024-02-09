package com.example.aksa.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aksa.LoginActivity
import com.example.aksa.R
import com.example.aksa.databinding.FragmentProfileBinding
import com.example.aksa.pref.user.UserPreference


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userPreference: UserPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        userPreference = UserPreference.getInstance(requireContext())

        binding.tvNameProfile.text = userPreference.getName()
        binding.tvEmailProfile.text = userPreference.getUserEmail()

        binding.logout.setOnClickListener {
            userPreference.logout()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

        }

        return binding.root
    }

}