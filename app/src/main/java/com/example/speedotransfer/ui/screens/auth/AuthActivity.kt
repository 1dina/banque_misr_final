package com.example.speedotransfer.ui.screens.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.data.source.remote.retrofit.RetrofitInstance
import com.example.speedotransfer.ui.routes.AuthNavGraph
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme
import com.example.speedotransfer.ui.viewmodels.auth.AuthViewModel
import com.example.speedotransfer.ui.viewmodels.auth.AuthViewModelFactory

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val apiService = RetrofitInstance.callable
            val authViewModel: AuthViewModel =
                viewModel(factory = AuthViewModelFactory(apiService, this))
            SpeedoTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    AuthNavGraph(navController, authViewModel)
                }

            }
        }
    }
}

