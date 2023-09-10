package com.example.onlineshopapp.repositories.site

import com.example.onlineshopapp.api.site.BlogApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class BlogRepository @Inject constructor(val api: BlogApi) {
    suspend fun getBlogs(): ServiceResponse<Blog> {

        return try {api.getBlog()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

        suspend fun getBlogsById(id :Long): ServiceResponse<Blog> {

            return try {api.getBlogById(id)
            }catch (e:Exception){
                ServiceResponse(status = "Exception", message = e.message)
            }
        }
}