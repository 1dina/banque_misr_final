package com.example.speedotransfer.ui.viewmodels.myCards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationRequest
import com.example.speedotransfer.data.source.remote.models.account.UserAccountsResponseItem
import com.example.speedotransfer.domain.usecases.CreateAccountUseCase
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyCardsViewModel(
    private val createAccountUseCase: CreateAccountUseCase,
    private val getUserAccountsUseCase: GetUserAccountsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<MyCardsUiState>(MyCardsUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getUserAccounts()
    }

    private fun getUserAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = MyCardsUiState.Loading
            try {
                val result = getUserAccountsUseCase.execute()
                if (result.isSuccess) {
                    val cards = result.getOrNull() ?: emptyList()
                    _uiState.value = MyCardsUiState.Success(cards)
                } else {
                    result.exceptionOrNull()?.let {
                        _uiState.value = MyCardsUiState.Error(it.message ?: "An error occurred")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MyCardsUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun createAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = MyCardsUiState.Loading
            try {
                val result = createAccountUseCase.execute(
                    AccountCreationRequest("visa", (1000..10000).random())
                )
                if (result.isSuccess) {
                    _uiState.value = MyCardsUiState.AccountCreationSuccess("You've successfully added your card")
                    getUserAccounts()
                } else {
                    result.exceptionOrNull()?.let {
                        _uiState.value = MyCardsUiState.Error(it.message ?: "Failed to create account")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MyCardsUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}