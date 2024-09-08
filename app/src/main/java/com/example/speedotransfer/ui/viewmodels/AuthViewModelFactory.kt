package com.example.speedotransfer.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.domain.usecases.LoginUserUseCaseImpl
import com.example.speedotransfer.domain.usecases.RegisterUserUseCaseImpl
import com.example.speedotransfer.repo.Repository

class AuthViewModelFactory(
    private val apiService: BankingAPICallable
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val authRepository = Repository(apiService)
            val authenticateUserUseCase = RegisterUserUseCaseImpl(authRepository)
            val loginUserUseCase = LoginUserUseCaseImpl(authRepository)
            return AuthViewModel(authenticateUserUseCase,loginUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}