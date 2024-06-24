package com.example.wishlistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<WishUiState> = MutableStateFlow(WishUiState())
    val uiState  = _uiState.asStateFlow()
    fun onWishTitleChange(newTitle: String) {
        _uiState.update { currentState->
            currentState.copy(
                wishTitle = newTitle
            )
        }
    }
    fun onWishDescriptionChange(newDesc : String) {
        _uiState.update { currentState->
            currentState.copy(
                wishDescription = newDesc
            )
        }
    }

    lateinit var getAllWishes : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun getWishById(id : Long) : Flow<Wish>{
        return wishRepository.getWishById(id)
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}