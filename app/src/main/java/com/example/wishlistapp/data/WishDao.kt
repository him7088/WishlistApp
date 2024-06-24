package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addWish(wish: Wish)

    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    @Query("SELECT * FROM `wish-table` WHERE id=:id")
    abstract fun getWishById(id : Long) : Flow<Wish>

}