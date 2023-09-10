package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDataBase
import com.example.onlineshopapp.db.dao.UserEntityDao
import com.example.onlineshopapp.db.model.UserEntity

class UserEntityRepository(application: Application) {
    private var userDao:UserEntityDao
    private lateinit var currentUserEntity:LiveData<UserEntity>
    init{
        val database=OnlineShopDataBase.getInstance(application)
        userDao=database.userDao()
        currentUserEntity=userDao.get()
    }

    suspend fun insert(user:UserEntity){
        deleteAll()
        userDao.add(user)
    }
    suspend fun update(user:UserEntity){
        userDao.update(user)
    }
    suspend fun delete(user:UserEntity){
        userDao.delete(user)
    }
       fun deleteAll(){
        return userDao.deleteAll()
    }
    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }
}