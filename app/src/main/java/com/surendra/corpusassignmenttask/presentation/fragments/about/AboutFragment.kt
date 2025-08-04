package com.surendra.corpusassignmenttask.presentation.fragments.about

//import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

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
        setupAboutContent()
    }

    private fun setupAboutContent() {
        binding.tvAboutTitle.text = getString(R.string.about_title)
        binding.tvAboutDescription.text = getString(R.string.about_description)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}