package com.example.speedotransfer.ui.viewmodels.myCards

import com.example.speedotransfer.data.source.remote.models.account.UserAccountsResponseItem

sealed class MyCardsUiState {
    object Idle : MyCardsUiState()
    object Loading : MyCardsUiState()
    data class Success(val cards: List<UserAccountsResponseItem>) : MyCardsUiState()
    data class Error(val message: String) : MyCardsUiState()
    data class AccountCreationSuccess(val message: String) : MyCardsUiState()
}