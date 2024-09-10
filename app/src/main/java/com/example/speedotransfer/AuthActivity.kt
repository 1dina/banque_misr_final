package com.example.speedotransfer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.data.source.BankingAPIService
import com.example.speedotransfer.routes.AppRoutes
import com.example.speedotransfer.routes.AuthNavGraph
import com.example.speedotransfer.ui.screens.dashboard.components.mycards.CardCurrency
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme
import com.example.speedotransfer.ui.viewmodels.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.AuthViewModelFactory

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val apiService = BankingAPIService.callable
            val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(apiService,this))
            SpeedoTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    AuthNavGraph(navController,authViewModel)
                }

                }
            }
        }
    }

