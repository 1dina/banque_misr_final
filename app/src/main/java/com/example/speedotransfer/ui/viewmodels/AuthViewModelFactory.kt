package com.example.speedotransfer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.domain.usecases.AuthenticateUserUseCaseImpl
import com.example.speedotransfer.repo.Repository

class AuthViewModelFactory(
    private val apiService: BankingAPICallable
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val authRepository = Repository(apiService)
            val authenticateUserUseCase = AuthenticateUserUseCaseImpl(authRepository)
            return AuthViewModel(authenticateUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}