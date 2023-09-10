package com.example.onlineshopapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productId: Long,
    var quantity: Int,
    var colorId :Long,
    var sizeId:Long,
    var image: String?,
    var price: Long?,
    var title: String?,
    var colorHex:String,
    var size:String

)
