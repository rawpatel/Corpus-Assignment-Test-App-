package com.surendra.corpusassignmenttask.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surendra.corpusassignmenttask.data.model.ContentSection
import com.surendra.corpusassignmenttask.data.repository.ContentRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContentRepository(application.applicationContext)

    private val _contentSections = MutableLiveData<List<ContentSection>>()
    val contentSections: LiveData<List<ContentSection>> = _contentSections

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadContent() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val sections = repository.getContentSections()
                _contentSections.value = sections
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}