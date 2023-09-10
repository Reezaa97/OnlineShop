package com.example.onlineshopapp.models.invoice


data class Transaction(
   val amount:Int?,
   var cardHolder:String?,
   var code:Int?,
   var customerPhone:String?,
   var id:Long?,
   var orderId:String?,
   var refId:String?,
   var refundRequest:String?,
   var shaparakRedId:String?,
   var transId:String?,
)
