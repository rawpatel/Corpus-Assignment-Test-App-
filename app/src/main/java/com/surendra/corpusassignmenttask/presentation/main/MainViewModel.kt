package com.surendra.corpusassignmenttask.presentation.main
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ContentRepository()

    private val _contentItems = MutableLiveData<List<ContentItem>>()
    val contentItems: LiveData<List<ContentItem>> = _contentItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadContent() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val items = repository.getContentItems()
                _contentItems.value = items
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}