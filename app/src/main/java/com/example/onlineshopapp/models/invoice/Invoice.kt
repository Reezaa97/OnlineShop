package com.example.onlineshopapp.models.invoice

import com.example.onlineshopapp.models.cusomer.Users


data class Invoice(
    var addData: String?,
    var id: Long?,
    var items:List<InvoiceItem>?,
    var paymentData:String?,
    var status:String?,
    var transaction:List<Transaction>?,
    var user: Users?

)
