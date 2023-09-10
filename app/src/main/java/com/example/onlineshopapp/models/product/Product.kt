package com.example.onlineshopapp.models.product



data class Product(
    var id: Long?=null,
    var addDate: String?="",
    var category: ProductCategory?=null,
    var colors: List<ProductColor>?=null,
    var description: String?="",
    var image: String?="",
    var price: Long?=0,
    var sizes: List<ProductSize>?=null,
    var title: String?="",
    var visitCount: Int?=0
)