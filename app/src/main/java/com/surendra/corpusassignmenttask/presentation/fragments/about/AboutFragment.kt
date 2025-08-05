package com.surendra.corpusassignmenttask.presentation.fragments.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.databinding.FragmentAboutBinding
import com.surendra.corpusassignmenttask.presentation.login.LoginActivity
import com.surendra.corpusassignmenttask.utils.PreferenceManager

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(requireContext())
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        binding.apply {
            textViewAppName.text = "Home Content App"
            textViewVersion.text = "Version 1.0.0"
            textViewDescription.text = getString(R.string.about_description)
            textViewDeveloper.text = "Developed"
        }
    }

    private fun setupClickListeners() {
        binding.buttonLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun performLogout() {
        // Clear login status
        preferenceManager.setUserLoggedIn(false)

        // Navigate to login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        // Finish current activity
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}