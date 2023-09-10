package com.example.onlineshopapp.models.invoice

import com.example.onlineshopapp.models.cusomer.UserVm


data class PaymentTransaction(
    var user: UserVm,
    var items:List<InvoiceItem>
)
