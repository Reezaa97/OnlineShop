package com.example.onlineshopapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id :Int=0,
    var address: String?,
    var customerId: Long,
    var firstName: String?,
    var lastName: String?,
    var phone: String?,
    var postalCode: String?,
    var token: String?,
    var username: String?,
     var userId:Long
         )

