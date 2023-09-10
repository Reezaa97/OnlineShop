package com.example.onlineshopapp.models.invoice

import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.models.product.Product


data class InvoiceItem(
    var id: Long?=null,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Long?=0,
) {

    companion object{
        fun convertFromBasket(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(
                product = Product(id = basketEntity.productId),
                quantity=basketEntity.quantity,

                )


        }
    }
}
