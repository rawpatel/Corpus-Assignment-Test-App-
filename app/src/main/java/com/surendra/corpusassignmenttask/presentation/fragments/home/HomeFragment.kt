package com.surendra.corpusassignmenttask.presentation.fragments.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.surendra.corpusassignmenttask.databinding.FragmentHomeBinding
import com.surendra.corpusassignmenttask.presentation.adapter.HomeContentAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        loadData()
    }

    private fun setupRecyclerView() {
        homeContentAdapter = HomeContentAdapter()
        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeContentAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.homeContent.collect { contentList ->
                if (contentList.isNotEmpty()) {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViewHome.visibility = View.VISIBLE
                    homeContentAdapter.submitList(contentList)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.error.collect { errorMessage ->
                errorMessage?.let {
                    binding.textViewError.text = it
                    binding.textViewError.visibility = View.VISIBLE
                    binding.recyclerViewHome.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun loadData() {
        homeViewModel.loadHomeContent(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}