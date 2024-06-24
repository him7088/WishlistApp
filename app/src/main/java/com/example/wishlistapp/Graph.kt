package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.data.WishDataBase
import com.example.wishlistapp.data.WishRepository

object Graph {
    lateinit var dataBase: WishDataBase

    val wishRepository by lazy {
        WishRepository(wishDao = dataBase.wishDao())
    }

    fun provide(context: Context) {
        dataBase = Room.databaseBuilder(context, WishDataBase::class.java,"wishlist.db").build()

    }

}