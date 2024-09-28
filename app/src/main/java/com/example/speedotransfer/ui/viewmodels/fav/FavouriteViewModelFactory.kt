package com.example.speedotransfer.ui.viewmodels.fav

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speedotransfer.data.source.remote.retrofit.BankingAPICallable
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.domain.usecases.DeleteFavUseCaseImpl
import com.example.speedotransfer.domain.usecases.GetAllFavUseCaseImpl
import com.example.speedotransfer.data.repo.DashboardRepositoryImpl

class FavouriteViewModelFactory(
    private val apiService: BankingAPICallable,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            val encryptedSharedPreferences = SecureStorageDataSource(context)
            val dashboardRepository =
                DashboardRepositoryImpl(apiService, encryptedSharedPreferences)
            val getAllFavUseCase = GetAllFavUseCaseImpl(dashboardRepository)
            val deleteFavUseCase = DeleteFavUseCaseImpl(dashboardRepository)
            return FavouriteViewModel(getAllFavUseCase,deleteFavUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}