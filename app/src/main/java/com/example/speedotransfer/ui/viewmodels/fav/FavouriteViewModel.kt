package com.example.speedotransfer.ui.viewmodels.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.domain.usecases.DeleteFavUseCase
import com.example.speedotransfer.domain.usecases.GetAllFavUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class FavouriteViewModel(
    private val getAllFavUseCase: GetAllFavUseCase,
    private val deleteFavUseCase: DeleteFavUseCase
) : ViewModel() {

    private val _favUiState = MutableStateFlow<FavouriteUiState>(FavouriteUiState.Idle)
    val favUiState = _favUiState.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    init {
        getAllFav()
    }

    private fun getAllFav() {
        viewModelScope.launch(Dispatchers.IO) {
            _favUiState.value = FavouriteUiState.Loading
            try {
                val result = getAllFavUseCase.execute()
                if (result.isSuccess) {
                    val favList = result.getOrNull()!!
                    _favUiState.value = FavouriteUiState.Success(favList)
                } else {
                    _favUiState.value = FavouriteUiState.Error("")
                    result.exceptionOrNull()?.let { handleError(it) }
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun deleteFromFav(accountId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _favUiState.value = FavouriteUiState.Loading
            try {
                val result = deleteFavUseCase.execute(accountId)
                if (result.isSuccess) {
                    _toastMessage.value = result.getOrNull()!!
                    val updatedFavList =
                        (_favUiState.value as? FavouriteUiState.Success)?.favListItems
                            ?.filter { it.accountId != accountId } ?: emptyList()
                    _favUiState.value = FavouriteUiState.Success(updatedFavList)
                } else {
                    _favUiState.value = FavouriteUiState.Error("")
                    result.exceptionOrNull()?.let { handleError(it) }
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(exception: Throwable) {
        val errorMessage = when (exception) {
            is UnknownHostException -> "Please check your internet connection."
            else -> "Error: Server took too long to respond."
        }
        _favUiState.value = FavouriteUiState.Error(errorMessage)
        _toastMessage.value = errorMessage
    }
}