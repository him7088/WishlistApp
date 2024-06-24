package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")
    val description : String = ""

)

object DummyWish{
    val wishList = mutableListOf(
        Wish(title = "Galaxy Watch" , description = "Samsung Smart Watch "),
        Wish(title = "Titan Watch", description = "An analog watch made by Titan"),
        Wish(title = "Adidas X_PLRBOOST", description = "Comfortable Sneakers from Adidas")
    )
}