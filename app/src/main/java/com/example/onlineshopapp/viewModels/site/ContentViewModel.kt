package com.example.onlineshopapp.viewModels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Content
import com.example.onlineshopapp.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repository: ContentRepository
) : ViewModel() {
    fun getContents(onResponse: (response: ServiceResponse<Content>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getContents()
            onResponse(response)
        }
    }

    fun getContentsByTitle(title :String,onResponse: (response: ServiceResponse<Content>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getContentsByTitle(title)
            onResponse(response)
        }
    }
}