package com.example.speedotransfer.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.remote.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.usecases.AddToFavUseCaseImpl
import com.example.speedotransfer.domain.usecases.DoTransferUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetInfoUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetTransactionsUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetUserAccountsUseCaseImpl
import com.example.speedotransfer.domain.usecases.UpdatePasswordUseCaseImpl
import com.example.speedotransfer.data.repo.DashboardRepositoryImpl

class HomeViewModelFactory(
    private val apiService: BankingAPICallable,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val encryptedSharedPreferences = SecureStorageDataSource(context)
            val dashboardRepository =
                DashboardRepositoryImpl(apiService, encryptedSharedPreferences)
            val getUserAccountsUseCase = GetUserAccountsUseCaseImpl(dashboardRepository)
            val doTransferUseCase = DoTransferUseCaseImpl(dashboardRepository)
            val getTransactionsUseCase = GetTransactionsUseCaseImpl(dashboardRepository)
            val getInfoUseCase = GetInfoUseCaseImpl(dashboardRepository)
            val updatePasswordUseCase = UpdatePasswordUseCaseImpl(dashboardRepository)
            val addToFavUseCase = AddToFavUseCaseImpl(dashboardRepository)
            return HomeViewModel(getUserAccountsUseCase,
            doTransferUseCase,getInfoUseCase,updatePasswordUseCase,getTransactionsUseCase,addToFavUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}