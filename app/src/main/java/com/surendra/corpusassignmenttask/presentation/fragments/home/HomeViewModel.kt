package com.surendra.corpusassignmenttask.presentation.fragments.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surendra.corpusassignmenttask.data.model.HomeContent
import com.surendra.corpusassignmenttask.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = HomeRepository()

    private val _homeContent = MutableStateFlow<List<HomeContent>>(emptyList())
    val homeContent: StateFlow<List<HomeContent>> = _homeContent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadHomeContent(context: Context) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val content = repository.getHomeContent(context)
                _homeContent.value = content

            } catch (e: Exception) {
                _error.value = "Failed to load content: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}