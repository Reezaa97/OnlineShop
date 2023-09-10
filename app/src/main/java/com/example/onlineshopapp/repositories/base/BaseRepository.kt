package com.example.onlineshopapp.repositories.base

import java.util.*

 open class BaseRepository {
    protected fun prepareToken(token: String): String {
        var fixedToken = token
        if (token.lowercase(Locale.getDefault()).startsWith("bearer ")) {
            fixedToken = "bearer $token"
        }
        return fixedToken
    }
}