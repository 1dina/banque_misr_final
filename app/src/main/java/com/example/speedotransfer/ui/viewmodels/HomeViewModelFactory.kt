package com.example.speedotransfer.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.usecases.CreateAccountUseCaseImp
import com.example.speedotransfer.domain.usecases.DoTransferUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetAccountByIdUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetTransactionsUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCaseImpl
import com.example.speedotransfer.repo.DashboardRepositoryImpl

class HomeViewModelFactory(
    private val apiService: BankingAPICallable,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val encryptedSharedPreferences = SecureStorageDataSource(context)
            val dashboardRepository =
                DashboardRepositoryImpl(apiService, encryptedSharedPreferences)
            val createAccountUseCase = CreateAccountUseCaseImp(dashboardRepository)
            val getUserAccountsUseCase = GetUserAccountsUseCaseImpl(dashboardRepository)
            val getAccountByIdUseCase = GetAccountByIdUseCaseImpl(dashboardRepository)
            val doTransferUseCase = DoTransferUseCaseImpl(dashboardRepository)
            val getTransactionsUseCase = GetTransactionsUseCaseImpl(dashboardRepository)
            return HomeViewModel(createAccountUseCase,getUserAccountsUseCase, getAccountByIdUseCase
            ,doTransferUseCase,getTransactionsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}