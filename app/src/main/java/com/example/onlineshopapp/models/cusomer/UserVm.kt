package com.example.onlineshopapp.models.cusomer

import com.example.onlineshopapp.db.model.UserEntity


data class UserVm(
    var address: String?="",
    var customerId: Long?=null,
    var firstName: String?="",
    var id: Long?=null,
    var lastName: String?="",
    var oldpassword: String?=null,
    var password: String?=null,
    var phone: String?="",
    var postalCode: String?="",
    var repeatPass: String?=null,
    var token: String?=null,
    var username: String?
){
        fun convertToEntity(): UserEntity {
            return UserEntity(
                userId = id!!,
                address = address,
                customerId = customerId!!,
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                postalCode = postalCode,
                token = token,
                username = username
            )


    }
}
