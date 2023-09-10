package com.example.onlineshopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlineshopapp.db.dao.BasketEntityDao
import com.example.onlineshopapp.db.dao.UserEntityDao
import com.example.onlineshopapp.db.model.BasketEntity
import com.example.onlineshopapp.db.model.UserEntity


@Database(entities = [UserEntity::class, BasketEntity::class], version = 6)
abstract class OnlineShopDataBase : RoomDatabase() {

    abstract fun userDao(): UserEntityDao
    abstract fun basketDao(): BasketEntityDao

    companion object {
        private var instance: OnlineShopDataBase? = null
        fun getInstance(context: Context): OnlineShopDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    OnlineShopDataBase::class.java, "onlineshop.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as OnlineShopDataBase
        }
    }
}