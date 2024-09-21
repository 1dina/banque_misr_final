package com.example.speedotransfer.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.models.favourite.FavouriteAdditionResponse
import com.example.speedotransfer.domain.usecases.DeleteFavUseCase
import com.example.speedotransfer.domain.usecases.GetAllFavUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class FavouriteViewModel(
    val getAllFavUseCase: GetAllFavUseCase,
    val deleteFavUseCase: DeleteFavUseCase
) : ViewModel() {
    private val _favListItems = MutableStateFlow<List<FavouriteAdditionResponse>>(
        emptyList()
    )
    val favListItems = _favListItems.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage = _toastMessage.asStateFlow()
    private val _responseStatus = MutableStateFlow<Boolean?>(null)

    init {
        getAllFav()
    }

    private fun getAllFav() {
        viewModelScope.launch {
            try {
                val result = getAllFavUseCase.execute()
                if (result.isSuccess) {
                    _favListItems.value = result.getOrNull()!!
                    _responseStatus.value = true
                    Log.e("trace Id", result.getOrNull().toString())
                } else {
                    result.exceptionOrNull()?.let { handleError(it) }
                }
            } catch (e: Exception) {
                handleError(e)
            }


        }
    }
    fun deleteFromFav(accountId : Int ){
        viewModelScope.launch {
            try {
                val result = deleteFavUseCase.execute(accountId)
                if (result.isSuccess) {
                    _toastMessage.value = result.getOrNull()!!
                    _responseStatus.value = true
                    _favListItems.value = _favListItems.value.filter { it.accountId != accountId }
                    Log.e("trace delete", result.getOrNull().toString())
                } else {
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

            else -> {
                val shownMessage = "server take long time to respond"
                "Error: $shownMessage"
            }
        }
        _responseStatus.value = false
        _toastMessage.value = errorMessage
    }

}