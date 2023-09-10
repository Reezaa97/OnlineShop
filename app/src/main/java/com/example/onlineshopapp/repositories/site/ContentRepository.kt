package com.example.onlineshopapp.repositories.site

import com.example.onlineshopapp.api.site.ContentApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Content
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class ContentRepository @Inject constructor(val api: ContentApi) {
    suspend fun getContents(): ServiceResponse<Content> {

        return try {api.getContent()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

        suspend fun getContentsByTitle(title: String): ServiceResponse<Content> {

            return try {api.getContentByTitle(title)
            }catch (e:Exception){
                ServiceResponse(status = "Exception", message = e.message)
            }
        }
}