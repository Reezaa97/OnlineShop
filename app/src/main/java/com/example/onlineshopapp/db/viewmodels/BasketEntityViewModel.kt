package com.example.onlineshopapp.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = BasketEntityRepository(application)

    var dataList = mutableStateOf<List<BasketEntity>>(listOf())

    private suspend fun insert(basket: BasketEntity) {
        repository.insert(basket)
    }

    private suspend fun update(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.update(basket)
    }

    suspend fun addToBasket(basket: BasketEntity) {
        if (dataList.value.any { x ->
                x.productId == basket.productId &&
                        x.colorId == basket.colorId && x.sizeId == basket.sizeId
            }) {
            val oldBasket =
                dataList.value!!.first { x ->
                    x.productId == basket.productId &&
                            x.colorId == basket.colorId && x.sizeId == basket.sizeId
                }

            oldBasket.quantity++
            update(oldBasket)
        } else {
            insert(basket)
        }
    }

    suspend fun delete(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.delete(basket)
    }
    suspend fun incrementQuantity(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.incrementQuantity(basket)
    }
    suspend fun decrementQuantity(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.decrementQuantity(basket)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    fun getAllBasketList(): List<BasketEntity> {
        return repository.getAllBasketList()
    }

    fun getAllBasketLive(): LiveData<List<BasketEntity>> {
        return repository.getAllBasketLive()
    }
}

