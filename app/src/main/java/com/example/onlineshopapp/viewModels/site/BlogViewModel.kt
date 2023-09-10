package com.example.onlineshopapp.viewModels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import com.example.onlineshopapp.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val repository: BlogRepository
) : ViewModel() {
    fun getBlogs(onResponse: (response: ServiceResponse<Blog>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlogs()
            onResponse(response)
        }
    }

    fun getBlogsById(id :Long,onResponse: (response: ServiceResponse<Blog>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getBlogsById(id)
            onResponse(response)
        }
    }
}