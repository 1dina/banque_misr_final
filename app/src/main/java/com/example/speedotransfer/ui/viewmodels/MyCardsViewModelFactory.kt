package com.example.speedotransfer.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.usecases.CreateAccountUseCaseImp
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCaseImpl
import com.example.speedotransfer.data.repo.DashboardRepositoryImpl

class MyCardsViewModelFactory(
    private val apiService: BankingAPICallable,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCardsViewModel::class.java)) {
            val encryptedSharedPreferences = SecureStorageDataSource(context)
            val dashboardRepository =
                DashboardRepositoryImpl(apiService, encryptedSharedPreferences)
            val createAccountUseCase = CreateAccountUseCaseImp(dashboardRepository)
            val getUserAccountsUseCase = GetUserAccountsUseCaseImpl(dashboardRepository)
            return MyCardsViewModel(createAccountUseCase,getUserAccountsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}