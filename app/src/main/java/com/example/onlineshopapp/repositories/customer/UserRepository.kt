package com.example.onlineshopapp.repositories.customer

import com.example.onlineshopapp.api.cusomer.UsersApi
import com.example.onlineshopapp.api.invoice.InvoiceApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.cusomer.UserVm
import com.example.onlineshopapp.models.cusomer.Users
import com.example.onlineshopapp.models.invoice.Invoice
import com.example.onlineshopapp.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(val api: UsersApi) : BaseRepository() {

    suspend fun getUserInfo(token: String): ServiceResponse<Users> {

        return try {
            api.getUserInfo(prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun changePassword(users: UserVm, token: String): ServiceResponse<Users> {

        return try {
            api.changePassword(users, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun login(users: UserVm): ServiceResponse<UserVm> {

        return try {
            api.login(users)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun register(users: UserVm): ServiceResponse<Users> {

        return try {
            api.register(users)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun update(users: UserVm, token: String): ServiceResponse<Users> {

        return try {
            api.update(users, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}