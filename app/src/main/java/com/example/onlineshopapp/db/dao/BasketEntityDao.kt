package com.example.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.onlineshopapp.db.model.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert
    fun add(basketEntity: BasketEntity)

    @Update
    fun update(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)


    @Query("delete from basketEntity")
    fun deleteAll()

    @Query("select * from basketEntity")
    fun getAll(): List<BasketEntity>

    @Query("select * from basketEntity")
    fun getAllLive():LiveData< List<BasketEntity>>
}
