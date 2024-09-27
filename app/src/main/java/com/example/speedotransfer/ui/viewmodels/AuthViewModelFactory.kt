package com.example.speedotransfer.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.usecases.LoginUserUseCaseImpl
import com.example.speedotransfer.domain.usecases.RegisterUserUseCaseImpl
import com.example.speedotransfer.domain.usecases.SignOutUseCaseImp
import com.example.speedotransfer.data.repo.AuthRepositoryImpl

class AuthViewModelFactory(
    private val apiService: BankingAPICallable,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val encryptedSharedPreferences = SecureStorageDataSource(context)
            val authAuthRepositoryImpl = AuthRepositoryImpl(apiService,encryptedSharedPreferences)
            val authenticateUserUseCase = RegisterUserUseCaseImpl(authAuthRepositoryImpl)
            val loginUserUseCase = LoginUserUseCaseImpl(authAuthRepositoryImpl)
            val signOutUseCase = SignOutUseCaseImp(authAuthRepositoryImpl)
            return AuthViewModel(authenticateUserUseCase,loginUserUseCase,signOutUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}