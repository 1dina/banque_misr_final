package com.example.speedotransfer.ui.viewmodels.fav

import com.example.speedotransfer.data.source.remote.models.favourite.FavouriteAdditionResponse

sealed class FavouriteUiState {
    data object Idle : FavouriteUiState()
    data object Loading : FavouriteUiState()
    data class Success(val favListItems: List<FavouriteAdditionResponse>) : FavouriteUiState()
    data class DeleteSuccess(val message: String) : FavouriteUiState()
    data class Error(val errorMessage: String) : FavouriteUiState()
}
