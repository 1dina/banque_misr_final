package com.example.speedotransfer.ui.viewmodels.home

import com.example.speedotransfer.data.source.remote.models.transaction.history.Content
import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse

sealed class HomeUiState {
    data object Idle : HomeUiState()
    data object Loading : HomeUiState()
    data class Success(
        val accountBalance: String,
        val transactionHistory: List<Content>,
        val userInfo: UserInfoResponse,
        val toastMessage: String? = null
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}